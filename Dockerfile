FROM openjdk:17
VOLUME /tmp
ADD build/libs/tw-book-0.0.1-SNAPSHOT.jar tw-book.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","tw-book.jar", "--spring.profiles.active=prod"]
