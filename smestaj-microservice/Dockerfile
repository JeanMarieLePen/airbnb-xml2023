FROM openjdk:17-jdk-oracle

WORKDIR /app

EXPOSE 8090

COPY target/smestaj-service.jar /app/smestaj-service.jar

ENTRYPOINT [ "java", "-jar",  "/app/smestaj-service.jar"]