# For Java 11, try this
FROM openjdk:11-jre-slim

EXPOSE 8085

# Refer to Maven build -> finalName
ARG JAR_FILE=../target/tgnotesagent-37.1.2-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]