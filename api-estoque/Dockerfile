FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/spring-microservices-estoque-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]