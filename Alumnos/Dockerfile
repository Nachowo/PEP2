FROM openjdk:17
ARG JAR_FILE=target/alumno-service.jar
COPY ${JAR_FILE} alumno-service.jar
ENTRYPOINT ["java","-jar","/alumno-service.jar"]