FROM openjdk:17-jdk-oracle
EXPOSE 5000
ADD target/reservation-service.jar reservation-service.jar
ENTRYPOINT [ "java", "-jar", "/reservation-service.jar"]
