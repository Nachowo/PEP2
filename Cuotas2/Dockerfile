FROM openjdk:17
ARG JAR_FILE=target/cuota-service.jar
COPY ${JAR_FILE} cuota-service.jar
ENTRYPOINT ["java","-jar","/cuota-service.jar"]