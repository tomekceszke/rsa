# Hello Cloud

This simple project demonstrates capabilities of Spring Boot and Spring Cloud frameworks to build distributed web application.

For the demo purposes it will implement RSA algorithm.

Micro services will be responsible for:
* finding the prime numbers, 
* generating public and private key,
* use them to encrypt and decrypt a message.


## Tech Stack

- Spring Boot
  - Asynchronous Methods - finding prime numbers
- Spring Cloud
  - Config Server
  - Netflix Eureka - service locator
  - Ribbon
  - Feign
  - Gateway
  - Sleuth + Zipkin - tracking
- Hibernate - persistence layer
- Thymeleaf - presentation layer
  

