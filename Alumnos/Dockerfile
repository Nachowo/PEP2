FROM openjdk:17
ARG JAR_FILE=target/AlumnosMicroService.jar
COPY ${JAR_FILE} AlumnosMicroService.jar
ENTRYPOINT ["java","-jar","/AlumnosMicroService.jar"]