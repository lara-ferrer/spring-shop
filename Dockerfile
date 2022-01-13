FROM openjdk:11
ADD target/spring-shop.jar spring-shop
ENTRYPOINT ["java", "-jar","spring-shop.jar"]
EXPOSE 8080