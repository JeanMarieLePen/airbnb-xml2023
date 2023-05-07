FROM openjdk:17-jdk-oracle

WORKDIR /app

EXPOSE 8091

COPY target/reservation-service.jar /app/reservation-service.jar

ENTRYPOINT [ "java", "-jar", "/app/reservation-service.jar"]
