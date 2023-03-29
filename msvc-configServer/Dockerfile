FROM openjdk:19-alpine
VOLUME /tmp
EXPOSE 8888
ADD ./target/msvc-configServer-0.0.1-SNAPSHOT.jar config-server.jar
ENTRYPOINT ["java", "-jar", "/config-server.jar"]