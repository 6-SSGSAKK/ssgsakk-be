FROM openjdk:17
COPY build/libs/ssgdotcom-0.0.1-SNAPSHOT.jar ssgdotcom-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ssgdotcom-0.0.1-SNAPSHOT.jar"]