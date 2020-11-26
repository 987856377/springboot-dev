FROM openjdk:8-jdk-alpine
MAINTAINER xuzhenkui <987856377@qq.com>
EXPOSE 8090
VOLUME /tmp
ADD springboot-dev-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]