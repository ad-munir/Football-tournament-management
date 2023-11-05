FROM openjdk:17-alpine
EXPOSE 8080
ADD target/master.jar master.jar

ENTRYPOINT ["java", "-jar", "/master.jar"]
