FROM openjdk:18
ARG JAR_FILE=target/notas-service.jar
COPY ${JAR_FILE} notas-service.jar
ENTRYPOINT ["java","-jar","/notas-service.jar"]