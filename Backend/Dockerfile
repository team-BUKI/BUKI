FROM adoptopenjdk:8-jdk-hotspot AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew

RUN rm -rf src/test
RUN ./gradlew clean build

FROM adoptopenjdk:8-jdk-hotspot
COPY --from=builder build/libs/*.jar app.jar/

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar"]