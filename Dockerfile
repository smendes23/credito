FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /creditoapp

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/credito-*.jar creditoapp.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "creditoapp.jar"]