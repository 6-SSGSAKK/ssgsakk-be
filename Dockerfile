FROM openjdk:11-jdk-slim
COPY build/libs/redistest-0.0.1-SNAPSHOT.jar redistest.jar
EXPOSE 8080
ENTRYPOINT exec java -jar redistest.jar