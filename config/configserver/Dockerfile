FROM gradle:jdk21-alpine AS builder
LABEL authors="CAOBANDOC"

COPY . /app

WORKDIR /app

RUN gradle build

FROM eclipse-temurin:21.0.1_12-jdk-ubi9-minimal

COPY --from=builder /app/build/libs/*.jar /app.jar
COPY --from=builder /app/ignore/ /ignore/

ENV GIT_URI=file:///ignore/
ENV EUREKASERVER_URI=http://host.docker.internal:8761/eureka

EXPOSE 8888

CMD ["java", "-jar", "/app.jar"]