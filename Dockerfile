# Step 1: Use Java 21 for build
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY mvnw .
RUN chmod +x mvnw
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B


COPY src src
RUN ./mvnw clean package -DskipTests

# Step 2: Use smaller runtime JRE image (Java 21)
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
