FROM amazoncorretto:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD apt-get update -y
ENTRYPOINT ["java","-jar","/app.jar"]
#docker build --build-arg  JAR_FILE=build/libs/MovieAppJava10-0.0.1-SNAPSHOT.jar -t onurbass/movieapp:v001 .