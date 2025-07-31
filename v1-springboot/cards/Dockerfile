# start with base img ie java runtime
FROM openjdk:17-jdk-slim

# information who maintains the img
LABEL maintainer="priyanka.com"

# add application jar to docker
COPY target/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

# cmd to execute application
ENTRYPOINT ["java", "-jar", "cards-0.0.1-SNAPSHOT.jar"]
