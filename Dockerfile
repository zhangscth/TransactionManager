FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
