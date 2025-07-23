package com.alea.mypokeapi_be;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Alvarobf0, 23/07/2025
 */
@SpringBootTest
class MyPokeApiBeApplicationTests {

    @Test
    void whenTestApplicationStarts_thenContextLoadsSuccessfully(ApplicationContext context) {
        assertNotNull(context);
    }

}
