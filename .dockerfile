FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia o jar gerado pelo Maven
COPY target/*.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]