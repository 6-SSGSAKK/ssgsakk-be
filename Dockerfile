<<<<<<< HEAD
FROM openjdk:11-jdk-slim
COPY build/libs/redistest-0.0.1-SNAPSHOT.jar redistest.jar
EXPOSE 8080
ENTRYPOINT exec java -jar redistest.jar
=======
FROM openjdk:17
COPY build/libs/ssgdotcom-0.0.1-SNAPSHOT.jar ssgdotcom-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ssgdotcom-0.0.1-SNAPSHOT.jar"]
>>>>>>> 35d355d64c452cf815fe08e6abcb9f55cc9b9418
