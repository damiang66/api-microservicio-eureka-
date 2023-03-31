FROM openjdk:19-alpine
VOLUME /tmp
EXPOSE 8005
ADD ./target/msvc-item-0.0.1-SNAPSHOT.jar msvc-item.jar
ENTRYPOINT ["java", "-jar", "/msvc-item.jar"]