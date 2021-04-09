FROM maven:3.6-slim AS build-common
WORKDIR /build

COPY pom.xml .
COPY challenge-common challenge-common
COPY challenge-api challenge-api
COPY challenge-jacoco challenge-jacoco

RUN mvn install -DskipTests --batch-mode

FROM springci/graalvm-ce:stable-java11-0.9.x AS release

COPY --from=build-common /build/challenge-api/target/*.jar /app.jar
#COPY docker-entrypoint.sh /
#RUN chmod +x /docker-entrypoint.sh
#ENTRYPOINT ["/docker-entrypoint.sh"]

ENTRYPOINT ["java","-jar","/app.jar"]