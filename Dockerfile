# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/primenumbers-0.0.1-SNAPSHOT.jar primenumbers.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "primenumbers.jar"]
