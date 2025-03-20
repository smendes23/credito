FROM openjdk:21-jdk
COPY target/credito-0.0.1-SNAPSHOT.jar credito.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "credito.jar"]