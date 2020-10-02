# Pricing Service

The Pricing Service is a REST WebService that simulates a backend that
would store and retrieve the price of a vehicle given a vehicle id as
input. In this project, you will convert it to a microservice.


## Features

- REST WebService integrated with Spring Boot

## Instructions

#### TODOs

- Convert the Pricing Service to be a microservice.
- Add an additional test to check whether the application appropriately generates a price for a given vehicle ID

#### Run the code

To run this service you execute:

```
$ mvn clean package
```

```
$ java -jar target/pricing-service-0.0.1-SNAPSHOT.jar
```

It can also be imported in your IDE as a Maven project.

## PROJECT SPECIFICATION

## Rubric
Convert the Pricing Service

|   |CRITERIA|MEETS SPECIFICATIONS|
|---|--------|--------------------|
|[ ]|The Pricing Service is converted to a microservice.|The Pricing Service API is converted to a microservice with Spring Data REST, without the need to explicitly include code for the Controller or Service.||
|[ ]|A Eureka server is used and the Pricing Service is registered with that server.| A Eureka server is implemented and running on port 8761. The Pricing Service is registered on that server and is named pricing-service.||
|[ ]|A Eureka server is used and the Pricing Service is registered with that server.| The Vehicles API should be able to use the Eureka server to call the pricing service.||
|[ ]|At least one additional test is added for the Pricing Service.| Within the test folder, at least one additional test has been implemented outside of contextLoads() for the overall Pricing Service Application.||
