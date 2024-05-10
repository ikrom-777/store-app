FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
ARG PROFILE=dev
ARG APP_VERSION=1.0.1


WORKDIR /app
COPY --from=build /build/target/store-backend-*.jar /app/

ENV DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/store_app
ENV ACTIVE_PROFLE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFLE} -Dspring.datasource.url=${DB_URL} store-backend-${JAR_VERSION}.jar