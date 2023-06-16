FROM openjdk:11 as build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} BornListApplication.jar
ENTRYPOINT ["java","-jar","BornListApplication.jar"]
