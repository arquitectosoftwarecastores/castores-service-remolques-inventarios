FROM openjre:8

ARG JAR_FILE=target/inventarios-1.0.0-SNAPSHOT.jar

#RUN mvn clean install
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
