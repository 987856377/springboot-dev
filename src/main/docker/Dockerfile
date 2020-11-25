FROM openjdk:8-jdk-alpine
MAINTAINER xuzhenkui <987856377@qq.com>
EXPOSE 8090
VOLUME /tmp
ADD target/springboot-dev-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' >/etc/timezone \