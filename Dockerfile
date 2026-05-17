FROM eclipse-temurin:17-jdk
LABEL authors="User"

COPY target/ex-1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
