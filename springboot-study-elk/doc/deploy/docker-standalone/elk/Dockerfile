FROM openjdk:8u312-jre
MAINTAINER test

VOLUME /tmp
ADD ./jar/*.jar /app.jar
RUN bash -c 'touch /app.jar'

ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT [ "java", "-jar", "/app.jar", "--spring.profiles.active=dev", "-Djava.security.egd=file:/dev/./urandom"]

EXPOSE 8080