FROM amazoncorretto:21-alpine-jdk
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar;
COPY ${JAR_FILE} app_healthy_pets.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_healthy_pets.jar"]