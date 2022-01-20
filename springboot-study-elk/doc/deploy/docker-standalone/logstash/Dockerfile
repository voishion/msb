FROM logstash:7.16.3
# MySQL连接器
COPY libs /usr/share/logstash/logstash-core/lib/jars
# 流水线配置
COPY pipelines.yml /usr/share/logstash/config/

# 创建目录
RUN mkdir -p /usr/share/logstash/config/default
# 业务配置
COPY conf/default/logstash-springboot.conf /usr/share/logstash/config/default/

# 创建目录
RUN mkdir -p /usr/share/logstash/config/elk
# 业务配置
COPY conf/elk/u_complaint_report.conf /usr/share/logstash/config/elk/
COPY conf/elk/u_complaint_reason.conf /usr/share/logstash/config/elk/
COPY conf/elk/u_complaint_image.conf /usr/share/logstash/config/elk/