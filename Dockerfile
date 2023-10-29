# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/customer-0.0.1-SNAPSHOT.jar /app/customer-0.0.1-SNAPSHOT.jar

# Make port 8080 available to the world outside this container
EXPOSE 8081

# Define environment variables
ENV SPRING_PROFILES_ACTIVE=default

# Run the JAR file
CMD ["java", "-jar", "customer-0.0.1-SNAPSHOT.jar"]
