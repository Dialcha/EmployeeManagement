FROM adoptopenjdk/openjdk8:latest
COPY employee-0.0.1-SNAPSHOT.jar /usr/src/bootdocker/employee-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/bootdocker
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/usr/src/bootdocker/employee-0.0.1-SNAPSHOT.jar"]