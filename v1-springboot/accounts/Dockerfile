# start with base img ie java runtime
FROM openjdk:17-jdk-slim

# information who maintains the img
LABEL maintainer="priyanka.com"

# add application jar to docker
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# cmd to execute application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]
