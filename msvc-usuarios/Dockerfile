FROM openjdk:19-alpine
VOLUME /tmp

ADD ./target/msvc-usuarios-0.0.1-SNAPSHOT.jar msvc-usuarios.jar
ENTRYPOINT ["java", "-jar", "/msvc-usuarios.jar"]