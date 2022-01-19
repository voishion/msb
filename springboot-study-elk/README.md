# ELK

[参考文档](https://blog.csdn.net/qq_21019419/article/details/103630081)

## 下载Docker镜像

```shell
docker pull elasticsearch:7.16.3
docker pull kibana:7.16.3
docker pull logstash:7.16.3
```

## 构建容器

1. 构建logstash的配置文件

   ```conf
   input {
     tcp {
       mode => "server"
       host => "0.0.0.0"
       port => 4560
       codec => json_lines
     }
   }
   output {
     elasticsearch {
       hosts => "es:9200"
       index => "springboot-logstash-%{+YYYY.MM.dd}"
     }
   }
   ```
   
2. 构建elasticsearch，使用docker-compose.yml构建

   ```yaml
   version: '3'
   services:
     elasticsearch:
       image: elasticsearch:7.16.3
       container_name: elasticsearch
       networks:
         elk_net:
           aliases:
             - elasticsearch
       environment:
         - "cluster.name=elasticsearch" #设置集群名称为elasticsearch
         - "discovery.type=single-node" #以单一节点模式启动
         - "ES_JAVA_OPTS=-Xms512m -Xmx1024m" #设置使用jvm内存大小
         - "TZ=Asia/Shanghai"
       volumes:
         - /Users/voishion/work/server/docker/elasticsearch/plugins:/usr/share/elasticsearch/plugins
         - /Users/voishion/work/server/docker/elasticsearch/data:/usr/share/elasticsearch/data
         - /Users/voishion/work/server/docker/elasticsearch/logs:/usr/share/elasticsearch/logs
       ports:
         - "9200:9200"
         - "9300:9300"
   
     kibana:
       image: kibana:7.16.3
       container_name: kibana
       networks:
         elk_net:
           aliases:
             - kibana
       links:
         - elasticsearch:es #可以用es这个域名访问elasticsearch服务
       depends_on:
         - elasticsearch #kibana在elasticsearch启动之后再启动
       environment:
         - "elasticsearch.hosts=http://es:9200" #设置访问elasticsearch的地址
         - "TZ=Asia/Shanghai"
       ports:
         - "5601:5601"
   
     logstash:
       image: logstash:7.16.3
       container_name: logstash
       networks:
         elk_net:
           aliases:
             - logstash
       links:
         - elasticsearch:es #可以用es这个域名访问elasticsearch服务
       volumes:
         - /Users/voishion/work/server/docker/logstash/pipeline/logstash-springboot.conf:/usr/share/logstash/pipeline/logstash.conf #挂载logstash的配置文件
       depends_on:
         - elasticsearch #kibana在elasticsearch启动之后再启动
       environment:
         - "xpack.monitoring.elasticsearch.hosts=http://es:9200" # 解决logstash监控连接报错
         - "TZ=Asia/Shanghai"
       ports:
         - "4560:4560"
   
   networks:
     elk_net:
       driver: bridge
   ```

3. 跑起来

   ```shell
   docker-compose up -d
   docker-compose up elasticsearch
   docker-compose up kibana
   docker-compose up logstash
   ```

4. 需要等待elasticsearch跑起来，大概几分钟，访问 [http://127.0.0.1:5601](http://127.0.0.1:5601/)

5. 如果出现错误，可以查看docker的日志

   ```shell
   docker logs elasticsearch
   ```

   一般出错大部分是内存不足导致的

   ![20191220120254793](doc/images/20191220120254793.png)

6. elasticsearch安装分词器

   > 第一种安装方法，如果下载速度很慢，可能超时或者失败

   ```shell
   docker exec -it elasticsearch bash
   # 安装
   ./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.16.3/elasticsearch-analysis-ik-7.16.3.zip
   # 退出容器
   exit
   # 重启logstash服务
   docker restart elasticsearch
   ```

   > 第二种安装方法，先本地下载好文件

   ```shell
   
   # 将下载解压的文件，copy到容器中，
   docker cp /Users/voishion/work/plugins/elasticsearch-analysis-ik-7.16.3 elasticsearch:/root
   # 进入容器
   docker exec -it elasticsearch /bin/bash
   # 创建分词器插件目录
   mkdir /usr/share/elasticsearch/plugins/ik/
   # 拷贝插件
   cd /root/
   cp -r elasticsearch-analysis-ik-7.16.3/* /usr/share/elasticsearch/plugins/ik/
   # 退出容器
   exit
   # 重启logstash服务
   docker restart elasticsearch
   ```

7. 在logstash中插件管理

   ```shell
   # 列出所有已安装插件
   bin/logstash-plugin list 
   # 列出所有已安装的插件，包括版本信息
   bin/logstash-plugin list --verbose 
   # 列出所有包括namefragment的已安装插件
   bin/logstash-plugin list '*namefragment*'
   # 列出指定组的已安装插件(input, filter, codec, output)
   bin/logstash-plugin list --group output 
   
   # 升级插件
   bin/logstash-plugin update 
   bin/logstash-plugin update logstash-output-kafka
   
   # 删除插件
   bin/logstash-plugin remove logstash-output-kafka
   ```

8. 在logstash中安装json_lines插件，先查看是否安装

   ```shell
   # 进入logstash容器
   docker exec -it logstash /bin/bash
   # 进入bin目录
   cd /bin/
   # 安装插件
   logstash-plugin install logstash-codec-json_lines
   # 退出容器
   exit
   # 重启logstash服务
   docker restart logstash
   ```

9. 项目集成logstash日志输出至elasticsearch，然后通过kibana查看




