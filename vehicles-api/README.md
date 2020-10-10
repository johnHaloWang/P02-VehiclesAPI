# Vehicles API

A REST API to maintain vehicle data and to provide a complete
view of vehicle details including price and address.

## Features

- REST API exploring the main HTTP verbs and features
- Hateoas
- Custom API Error handling using `ControllerAdvice`
- Swagger API docs
- HTTP WebClient
- MVC Test
- Automatic model mapping

## Instructions

#### TODOs

- Implement the `TODOs` within the `CarService.java` and `CarController.java`  files
- Add additional tests to the `CarControllerTest.java` file based on the `TODOs`
- Implement API documentation using Swagger

#### Run the Code

To properly run this application you need to start the Orders API and
the Service API first.


```
$ mvn clean package
```

```
$ java -jar target/vehicles-api-0.0.1-SNAPSHOT.jar
```

Import it in your favorite IDE as a Maven Project.

## Operations

Swagger UI: http://localhost:8080/swagger-ui.html

### Create a Vehicle

`POST` `/cars`
```json
{
   "condition":"USED",
   "details":{
      "body":"sedan",
      "model":"Impala",
      "manufacturer":{
         "code":101,
         "name":"Chevrolet"
      },
      "numberOfDoors":4,
      "fuelType":"Gasoline",
      "engine":"3.6L V6",
      "mileage":32280,
      "modelYear":2018,
      "productionYear":2018,
      "externalColor":"white"
   },
   "location":{
      "lat":40.73061,
      "lon":-73.935242
   }
}
```

### Retrieve a Vehicle

`GET` `/cars/{id}`

This feature retrieves the Vehicle data from the database
and access the Pricing Service and Boogle Maps to enrich 
the Vehicle information to be presented

### Update a Vehicle

`PUT` `/cars/{id}`

```json
{
   "condition":"USED",
   "details":{
      "body":"sedan",
      "model":"Impala",
      "manufacturer":{
         "code":101,
         "name":"Chevrolet"
      },
      "numberOfDoors":4,
      "fuelType":"Gasoline",
      "engine":"3.6L V6",
      "mileage":32280,
      "modelYear":2018,
      "productionYear":2018,
      "externalColor":"white"
   },
   "location":{
      "lat":40.73061,
      "lon":-73.935242
   }
}
```

### Delete a Vehicle

`DELETE` `/cars/{id}`


## Rubric
Implement the Vehicles API

|     |CRITERIA|MEETS SPECIFICATIONS|
|-|--------|--------------------|
|[X]|The Vehicles API can perform READ operations.| A Eureka server is implemented and running on port 8761.<br>The Pricing Service is registered on that server and is named pricing-service.|
|[X]|The Vehicles API can perform UPDATE operations.| The Vehicles API can update an existing vehicle through input from the user. |
|[X]|The Vehicles API can perform DELETE operations.| The Vehicles API can delete an existing vehicle when requested by the user.|
|[X]|The Vehicles API can request location data from Boogle Maps, and pricing data from the Pricing Service.| The Vehicles API is able to consume information from the separate Boogle Maps and Pricing Service APIs, and return that information as part of the vehicle information for a single vehicle. <br> Note: Boogle Maps will assign a new random address each time a query is called, so don't fret if it changes between queries. |

Testing the Vehicles API

|     |CRITERIA|MEETS SPECIFICATIONS|
|-|--------|--------------------|
|[X]|Tests are implemented for the Vehicles API CarController that cover the CRUD (Create) operations.| Within the CarControllerTest.java file, the TODOs for tests of CRUD operations have been implemented. <br> You are welcome to add additional tests beyond these as desired!|
|[X]|Tests are implemented for the Vehicles API CarController that cover the CRUD (Read) operations.| Within the CarControllerTest.java file, the TODOs for tests of CRUD operations have been implemented. <br> You are welcome to add additional tests beyond these as desired!|
|[N]|Tests are implemented for the Vehicles API CarController that cover the CRUD (Update) operations.| Within the CarControllerTest.java file, the TODOs for tests of CRUD operations have been implemented. <br> You are welcome to add additional tests beyond these as desired!|
|[X]|Tests are implemented for the Vehicles API CarController that cover the CRUD (Delete) operations.| Within the CarControllerTest.java file, the TODOs for tests of CRUD operations have been implemented. <br> You are welcome to add additional tests beyond these as desired!|
|[X]|All necessary dependencies are included in each Application's POM files, and the code runs successfully.| All necessary dependencies have been added to the relevant POM files, and the code is able to run both from tests and in launching the actual applications. |

API Documentation

|     |CRITERIA|MEETS SPECIFICATIONS|
|-|--------|--------------------|
|[X]|API documentation for the Vehicles API is implemented using Swagger.| API documentation for the Vehicles API is implemented using Swagger, and all related API queries are able to be run from there. The documentation is available at http://localhost:8080/swagger-ui.html when the application is running.<br><br>Note: You are welcome to add Swagger API documentation for the other APIs, but it is not required. |