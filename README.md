# IDEXX Animana test application

Application returns maximum of 5 books and maximum of 5 albums that are
related to the input term. The response table contains title,
authors(/artists) and information whether it's a book or an album.


Application uses

- iTunes API:
  [https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching]()
- Google Books API:
  [https://developers.google.com/books/docs/v1/reference/volumes/list]()

## Technology / mechanism:

- Spring boot
- Actuator with micrometer
- Webflux
- Lombok
- Spring boot test
- JUnit 5
- jQuery

For making requests to downstream services was used web client Webflux.
It provides stability and follows the response time of upstream service.
Webflux uses reactive mechanisms. Based on Webflux response timeout was
created simple circuit breaker interaction pattern. Spring boot
actuators with micrometer can be used for getting metrics.

### Tests

The application has some *unit tests* and some *end-to-end test*. End-to-end
tests have symbols 'IT' in classes name and should be runed manually.

## How to run the application

### Prerequisites

- Java 8
- Maven 3.x

### Build and run the server

./mvnw clean spring-boot:run

### Open UI

Open URL http://{host}:{port}/index.html in a browser

## Swagger

http://{host}:{port}/swagger-ui.html

## EndPoint

Application provides one endpoint that accepts text search term:
- GET http://{host}:{port}/v1/booksAlbums/{searchTerm}

## Configuration

In the file application.properties you can update:
- search.limit=5 - how many books and albums you will see on UI
- endpoint.response.timeout=20 - timeout for each downstream services

