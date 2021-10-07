FROM openjdk:8-jdk-alpine AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew

ENV TZ=Asia/Seoul
RUN apt-get update
RUN apt-get install -y tzdata

RUN rm -rf src/test
RUN ./gradlew bootjar

FROM openjdk:8-jdk-alpine
COPY --from=builder build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]