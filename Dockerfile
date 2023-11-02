FROM openjdk:17-alpine
COPY build/libs/review_bookmark-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
