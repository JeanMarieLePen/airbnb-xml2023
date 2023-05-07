FROM openjdk:17-jdk-oracle

#WORKDIR /app

EXPOSE 8082

#copy umesto add-a
ADD target/reglog.jar /reglog.jar

ENTRYPOINT [ "java", "-jar", "/reglog.jar"]

