FROM maven:3.9.4-eclipse-temurin-21 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM eclipse-temurin:21

COPY --from=build /app/target/library-0.0.1-SNAPSHOT.jar /app/library.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "library.jar"]