From openjdk:8
copy ./target/ecommerce-0.0.1-SNAPSHOT.jar ecommerce-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","ecommerce-0.0.1-SNAPSHOT.jar"]