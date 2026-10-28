FROM maven:3.9.11-eclipse-temurin-25 AS build

WORKDIR /workspace
COPY . .

ARG SERVICE_MODULE=api-gateway
RUN mvn -B -ntp -f "backend/${SERVICE_MODULE}/pom.xml" -DskipTests package \
    && artifact="$(find "backend/${SERVICE_MODULE}/target" -maxdepth 1 -type f -name '*.jar' ! -name '*.original' -print -quit)" \
    && test -n "$artifact" \
    && cp "$artifact" /workspace/application.jar

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /workspace/application.jar /app/application.jar
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=60 -XX:InitialRAMPercentage=10"
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "/app/application.jar"]