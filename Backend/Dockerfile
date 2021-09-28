FROM openjdk:8-jdk-alpine AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew

RUN rm -rf src/test
RUN ./gradlew bootjar

FROM openjdk:8-jdk-alpine
COPY --from=builder build/libs/*.jar app.jar

EXPOSE 9000
ENTRYPOINT ["java", "-jar", "/app.jar"]