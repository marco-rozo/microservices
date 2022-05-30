FROM openjdk:17-alpine

COPY ./target/rabbitmq-app-1.0.jar ~/jar/rabbitmq.jar

ENTRYPOINT ["java","-jar","~/jar/rabbitmq.jar"]
