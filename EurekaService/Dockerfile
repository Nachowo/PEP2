FROM openjdk:17
ARG JAR_FILE=target/EurekaService.jar
COPY ${JAR_FILE} EurekaService.jar
ENTRYPOINT ["java","-jar","/EurekaService.jar"]