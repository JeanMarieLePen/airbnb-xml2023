FROM openjdk:17-jdk-oracle

#WORKDIR /app

EXPOSE 8082

#copy umesto add-a
ADD target/reglog.jar /reglog.jar

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .
ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar"

ENTRYPOINT [ "java", "-jar", "reglog.jar"]

