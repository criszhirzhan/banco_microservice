# Use a base OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy Maven files first to leverage caching
COPY pom.xml .
COPY src ./src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean install -DskipTests

# Expose the app port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/banco-clientes-api.jar"]