FROM openjdk:17-jdk-slim

ENV HOME /home
WORKDIR /home/app

ARG JAR_FILE=booking-core-notification-service-1.0-SNAPSHOT.jar
ARG JAR_FILE_SOURCE=build/libs/${JAR_FILE}

COPY ${JAR_FILE_SOURCE}  ${JAR_FILE}

ENTRYPOINT ["java","-jar","booking-core-notification-service-1.0-SNAPSHOT.jar"]