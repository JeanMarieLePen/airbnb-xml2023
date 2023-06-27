FROM openjdk:17-jdk-oracle

#WORKDIR /app

EXPOSE 8091

COPY target/reservation-service.jar reservation-service.jar
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .
ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar"
ENTRYPOINT [ "java", "-jar", "reservation-service.jar"]
