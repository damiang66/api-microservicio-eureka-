FROM openjdk:19-alpine
VOLUME /tmp
#EXPOSE 8888
ADD ./target/msvc-productos-0.0.1-SNAPSHOT.jar msvc-productos.jar
ENTRYPOINT ["java", "-jar", "/msvc-productos.jar"]