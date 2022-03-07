FROM openjdk:17
EXPOSE 8080
ADD target/Logistics-0.0.1-SNAPSHOT.jar logistics.jar
ENTRYPOINT ["java", "-jar", "/logistics.jar"]