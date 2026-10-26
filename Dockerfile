FROM maven:3.9-eclipse-temurin-25 AS build

ARG MODULE
WORKDIR /workspace
COPY backend/${MODULE}/pom.xml ./pom.xml
COPY backend/${MODULE}/src ./src
RUN mvn --batch-mode --no-transfer-progress -DskipTests package

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /workspace/target/*.jar ./app.jar
ENTRYPOINT ["sh", "-c", "exec java -Dserver.port=${PORT:-10000} -jar /app/app.jar"]
