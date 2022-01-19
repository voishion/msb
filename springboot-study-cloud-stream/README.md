# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/#build-image)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/docs/current/reference/html/_reference.html#kafka-streams)
* [Apache Kafka Streams Binding Capabilities of Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/#_kafka_streams_binding_capabilities_of_spring_cloud_stream)

### Guides

The following guides illustrate how to use some features concretely:

* [Samples for using Apache Kafka Streams with Spring Cloud stream](https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/kafka-streams-samples)

### [MacOS下使用docker安装kafka](https://segmentfault.com/a/1190000022727674)

> 注意:由于 macOS 的 docker 底层实现的不同，网上的很多教程放在 macOS 中并不能成功运行，主要原因是 macOS 的 docker 在容器和宿主之间无法通过 ip 直接通信。因此在安装的时候需要特殊注意与 ip 相关的设置，当容器需要访问宿主ip时，需要使用`docker.for.mac.host.internal`或者`host.docker.internal`代替。  

```shell
host.docker.internal
docker.for.mac.host.internal
```

拉取镜像

```shell
docker pull wurstmeister/zookeeper
docker pull wurstmeister/kafka
```

启动容器

1. 启动 zookeeper

```shell
docker run -d --log-driver json-file --log-opt max-size=100m --log-opt max-file=2 --name zookeeper -p 2181:2181 -e TZ=Asia/Shanghai -v /etc/localtime:/etc/localtime wurstmeister/zookeeper
```

2. 启动 kafka

```shell
docker run -d --log-driver json-file --log-opt max-size=100m --log-opt max-file=2 --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=docker.for.mac.host.internal:2181/kafka -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -e TZ=Asia/Shanghai -v /etc/localtime:/etc/localtime wurstmeister/kafka
```

