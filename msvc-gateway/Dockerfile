FROM openjdk:19-alpine
VOLUME /tmp
EXPOSE 8090
ADD ./target/msvc-gateway-0.0.1-SNAPSHOT.jar msvc-gateway.jar
ENTRYPOINT ["java", "-jar", "/msvc-gateway.jar"]