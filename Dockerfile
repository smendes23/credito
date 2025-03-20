FROM openjdk:21-jdk
COPY target/credito-0.0.1-SNAPSHOT.jar credito.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "credito.jar"]