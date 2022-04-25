### 一、序言

延迟任务应用广泛，延迟任务典型应用场景有`订单超时自动取消`；`支付回调重试`。其中订单超时取消具有幂等性属性，无需考虑重复消费问题；支付回调重试需要考虑重复消费问题。

延迟任务具有如下特点：在未来的某个时间点执行；一般仅执行一次。

##### 1、实现原理

生产者将带有延迟信息的消息发送到RabbitMQ交换机中，等待延迟时间结束方将消息转发到绑定的队列中，消费者通过监听队列消费消息。延迟任务的关键在消息在交换机中停留。

显而易见，基于RabbitMQ实现延迟任务对服务器的可靠性要求极高，交换机内部消息无持久化机制，比如单机模式服务重启，未开始的延迟任务均丢失。

##### 2、组件选型

![jishuxuanxing](https://www.altitude.xin/typora/jishuxuanxing.jpg)

### 二、方案设计

#### （一）服务器

RabbitMQ服务需要安装`x-delayed-message`插件以处理延迟消息，[Docker安装RabbitMQ并安装延迟插件](https://blog.csdn.net/Amourtani/article/details/123824863)。

#### （二）生产者

延迟任务的实现对生产者的要求是将消息可靠的投递到交换机，因此使用`confirm确认`机制即可。

订单生成之后，先入库，然后以订单ID为key将订单详情存入Redis中（持久化），向RabbitMQ发送异步confirm确定请求。如果收到正常投递返回，则删除Redis中订单ID为key的数据，回收内存，否则以订单ID为key，从Redis中查询出订单数据，重新发送。

![shengchanzhu](https://www.altitude.xin/typora/shengchanzhu.jpg)

#### （三）消费者

延迟任务的实现对消费者的要求是以信息不丢失的方式消费消息，具体表现在：手动确认消息的消费，防止消息丢失；消费端持续稳定，防止消息堆积；消息消费失败有重试机制。

考虑到订单延迟取消属于幂等性操作，因此无需考虑消息的重复消费问题。

#### （一）生产者

考虑到下单是极为重要的操作，因此首先将订单落库、存盘，然后进行后续操作。

```java
for (long i = 1; i <= 10; i++) {
    /* 1.模拟生成订单 */
    BuOrder order = createOrder(i);
    /* 2.订单入库 */
    orderService.removeById(order);
    orderService.saveOrUpdate(order);
    /* 3.将订单存入信息Redis */
    RedisUtils.setObject(RabbitTemplateConfig.ORDER_PREFIX + i, order);
    /* 4.向RabbitMQ异步投递消息 */
    rabbitTemplate.convertAndSend(RabbitmqConfig.DELAY_EXCHANGE_NAME, RabbitmqConfig.DELAY_KEY, order, RabbitUtils.setDelay(30000), RabbitUtils.correlationData(order.getOrderId()));
}
```

生产者可靠投递消息

```java
public void confirm(CorrelationData correlationData, boolean ack, String cause) {
    if (correlationData == null) {
        return;
    }
    String key = ORDER_PREFIX + correlationData.getId();
    if (ack) {
        /* 如果消息投递成功，则删除Redis中订单数据，回收内存 */
        RedisUtils.deleteObject(key);
    } else {
        /* 从Redis中读取订单数据，重新投递 */
        BuOrder order = RedisUtils.getObject(key, BuOrder.class);
        /* 重新投递消息 */
        rabbitTemplate.convertAndSend(RabbitmqConfig.DELAY_EXCHANGE_NAME, RabbitmqConfig.DELAY_KEY, order, RabbitUtils.setDelay(30000), RabbitUtils.correlationData(order.getOrderId()));
    }
}
```

#### （二）消费者

消费者端手动确认，避免消息丢失；失败自动重试。

```java
@RabbitListener(queues = RabbitmqConfig.DELAY_QUEUE_NAME)
public void consumeNode01(Channel channel, Message message, BuOrder order) throws IOException {
    if (Objects.equals(0, order.getOrderStatus())) {
        /* 修改订单状态，设置为关闭状态 */
        orderService.updateById(new BuOrder(order.getOrderId(), -1));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info(String.format("消费者节点01消费编号为【%s】的消息", order.getOrderId()));
    }
}
```

消费者可靠消费应至少开启两个及以上应用，确保消息队列中不积压消息。