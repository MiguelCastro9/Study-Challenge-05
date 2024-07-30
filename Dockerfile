FROM gradle:7.5.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle build --no-daemon

FROM openjdk:17
EXPOSE 8080
COPY --from=build /home/gradle/project/build/libs/service.jar /app/service.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/service.jar"]
