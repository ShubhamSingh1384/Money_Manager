# Stage 1: Build JAR using Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom and source code
COPY pom.xml .
COPY src ./src

# Build JAR, skip tests for speed
RUN mvn clean package -DskipTests

# Stage 2: Run JAR with Eclipse Temurin JRE
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the JAR from the build stage, wildcard ensures it works even if filename changes
COPY --from=build /app/target/*SNAPSHOT.jar moneymanager-v1.0.jar

# Expose port your app runs on
EXPOSE 9090

# Command to run the app
ENTRYPOINT ["java", "-jar", "moneymanager-v1.0.jar"]
