FROM openjdk:17-jdk-oracle

#WORKDIR /app

EXPOSE 8761

ADD target/eureka-service.jar /eureka-service.jar

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .
ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar"


ENTRYPOINT [ "java", "-jar", "eureka-service.jar"]

