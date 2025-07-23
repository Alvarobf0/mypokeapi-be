package com.alea.mypokeapi_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Alvarobf0, 22/07/2025
 */
@EnableJpaRepositories
@SpringBootApplication
public class MyPokeApiBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPokeApiBeApplication.class, args);
    }

}
