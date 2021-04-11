FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY application.properties application.properties
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=dev", "--logging.level.org.springframework=INFO"]

HEALTHCHECK --interval=10s --timeout=10s --retries=10 CMD http_proxy="" wget -q --spider http://localhost:8080/credicardservice/actuator/health || exit 1
EXPOSE 8080

