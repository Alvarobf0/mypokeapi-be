# MyPokeAPI

Java Spring Boot application that exposes a REST API with 3 custom endpoints using the data from the [PokéAPI](https://pokeapi.co/api/v2/).

- `GET /pokemon/weight/top-5`  
  Returns the 5 heaviest Pokemon.

- `GET /pokemon/height/top-5`  
  Returns the 5 highest Pokemon.

- `GET /pokemon/base-exp/top-5`  
  Returns the 5 Pokemon with more base XP.

Includes Unit and Integration tests of the different layers and Swagger for API testing.

--------------------------

## How to run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Alvarobf0/mypokeapi-be.git](https://github.com/Alvarobf0/mypokeapi-be.git)
    cd mypokeapi-be
    ```
2.  **Build the project using Maven:**
    ```bash
    mvn clean install
    ```
3. **Run the application:**
    Open the project in your IDE and run `MyPokeApiBeApplication`.

The application will start on port `8080` by default and Swagger is found in http://localhost:8080/swagger-ui/index.html.

--------------------------

## Architectural & Technical Decisions

Due to the requirements I decided to use a Controller, Service, Repository layer approach. 

First and taking into account which data is needed for the endpoints, I created the `PokemonEntity.java`. It only contains some of the fields that the PokeAPI provides for Pokemon.

In `MyPokeApi.java` I added OpenAPI annotations so the exposed API can be used from Swagger. Some extra api responses are present in case some request failed, however they don't have a dedicated return type.
I also decided to set the path of the API as `/pokemon/(attribute)/top-5` since the requirements were separating the endpoints by attributes, and it can also allow in the future other returns such as `/pokemon/(attribute)/max`.

`MyPokeController.java` implements `MyPokeApi.java` interface and calls the service to obtain the data and generate the response.

For the service I create an interface and an implementation class `MyPokeServiceImpl.java` to ensure scalability and abstraction. There, all the business logic is found with the key methods that return the desired data, they mainly call the repository.

In the service is also found the method `loadPokemonData()` since the management of the data was a key point. I had two options regarding how will the endpoints work:
* Executing real-time requests to the PokeAPI everytime one of the endpoint was executed, obtain, process and output the data.
* Load all the data from the PokeAPI at the start-up of the application, store it in a repository and access it when calling the endpoints.

Taking into account that the second option makes the start-up of the application slower, it also presents the benefits of future scalability and usage of JPA. I also considered that application will remain ON so the start-up delay its acceptable.
Apart from that delay, it could bring data consistency problems if the PokeAPI changed frequently, but since data is quite static it wasn't taken into consideration.

To make the PokeAPI connection I created the `PokeApiClient.java` class using RestTemplate to fetch the data. I created the required DTOs to receive the data and map it to the Pokemon entity. Since the PokeAPI has a couple of parameters I also included them
as properties of the application in addition with the PokeAPI Url.

Finally, the data is populated in `MyPokeRepository.java` that through JPA query methods, filters and returns the desired data.

I created basic tests for the Client, Controller, Repository and Service. In case of the Repository, I created an integration test with the whole test application running.

--------------------------

## Future Improvements

* Adapt the `PokeApiClient` algorithm to support different values of the "offset" and "limit" parameters, since now the data will not be completely filled if different parameters are used.
* Do error handling of PokeAPI in `PokeApiClient` in case requests fail.
* Improve the error handling in `MyPokeApi` by creating a ControllerAdvice and adding dedicated error response DTOs.
* Improve testing with more robust tests and exceptions.
* Add caching if the application scales.
