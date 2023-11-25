FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY /target/gradprojectnov-0.0.1-SNAPSHOT.jar /app/gradprojectnov.jar
CMD ["java", "-jar", "gradprojectnov.jar"]
EXPOSE 8080