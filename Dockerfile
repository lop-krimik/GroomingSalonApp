FROM eclipse-temurin:21
WORKDIR /app
COPY /target/GroomingSalonApp-0.0.1-SNAPSHOT.jar /app/user.jar
ENTRYPOINT ["java","-jar","user.jar"]
EXPOSE 8082
