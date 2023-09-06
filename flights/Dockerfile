FROM openjdk:17-jdk-oracle
EXPOSE 8084
ADD target/FlightsApp.jar FlightsApp.jar
ENTRYPOINT [ "java", "-jar", "/FlightsApp.jar" ]
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .
ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar"
ENTRYPOINT [ "java", "-jar",  "FlightsApp.jar"]