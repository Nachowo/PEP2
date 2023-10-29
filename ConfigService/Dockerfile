FROM openjdk:18
ARG JAR_FILE=config-service/*.jar
COPY ${JAR_FILE} config-service.jar
ENTRYPOINT ["java","-jar","/config-service.jar"]