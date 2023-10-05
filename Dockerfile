FROM maven:3.9.4-eclipse-temurin-17 as maven
WORKDIR /app
COPY . /app
RUN mvn install -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=maven /app/target/test-1.0-SNAPSHOT.jar app.jar
CMD java -jar app.jar