FROM maven:3.6.3-ibmjava-8-alpine

WORKDIR /app
COPY pom.xml /app/pom.xml
RUN mvn -B -e -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.1.1:go-offline
COPY src/ /app/src
RUN mvn package

FROM openjdk:8-jre-alpine
COPY --from=target /app/target/*.jar /app/javapoker.jar
WORKDIR /app
EXPOSE 8000

ENTRYPOINT ["java", "-jar", "javapoker.jar"]