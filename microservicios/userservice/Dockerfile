FROM gradle:jdk21-alpine AS builder
LABEL authors="CAOBANDOC"

COPY . /app

WORKDIR /app

RUN gradle build

FROM eclipse-temurin:21.0.1_12-jdk-ubi9-minimal

COPY --from=builder /app/build/libs/*.jar /app.jar

ENV CONFIGSERVER_URI=http://host.docker.internal:8888

EXPOSE 8082

CMD ["java", "-jar", "/app.jar"]