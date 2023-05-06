FROM openjdk:17-jdk-oracle
EXPOSE 8083
ADD target/email-service.jar email-service.jar
#<plugin>
#    <groupId>org.apache.maven.plugins</groupId>
#    <artifactId>maven-surefire-plugin</artifactId>
#    <version>2.20.1</version>
#    <configuration>
#         <skipTests>true</skipTests>
#    </configuration>
#</plugin>
#Ovaj dependency u pom.xml da bi se preskocilo testiranje konekcije
#RUN mvn install -Dmaven.test.skip -DskipTests
ENTRYPOINT [ "java", "-jar", "/email-service.jar"]

