FROM java:8-jdk-alpine
COPY ./target/greeting-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","greeting-0.0.1-SNAPSHOT.jar"]