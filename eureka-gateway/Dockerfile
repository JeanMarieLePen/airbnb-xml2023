FROM openjdk:17-jdk-oracle
EXPOSE 7777
ADD target/gateway-service.jar gateway-service.jar
ENTRYPOINT [ "java", "-jar", "/gateway-service.jar"]

