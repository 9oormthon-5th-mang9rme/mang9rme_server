# Use the official OpenJDK 11 image as the base image
FROM openjdk:11-jre-slim AS builder

# Set the working directory to /app
WORKDIR /app

COPY build/libs/manggoormy-0.0.1-SNAPSHOT.jar app.jar

# Run the application
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]