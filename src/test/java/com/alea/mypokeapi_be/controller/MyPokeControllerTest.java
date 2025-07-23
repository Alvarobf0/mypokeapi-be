package com.alea.mypokeapi_be.controller;

import com.alea.mypokeapi_be.service.MyPokeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alvarobf0, 23/07/2025
 */
@ExtendWith(MockitoExtension.class)
class MyPokeControllerTest {

    private MockMvc mockMvc;

    private final String BASE_URL = "/mypokeapi";
    private final String HEAVIEST_POKEMON_URL = BASE_URL + "/pokemon/weight/top-5";
    private final String HIGHEST_POKEMON_URL = BASE_URL + "/pokemon/height/top-5";
    private final String BASE_EXPERIENCED_POKEMON_URL = BASE_URL + "/pokemon/base-exp/top-5";

    @InjectMocks
    private MyPokeController myPokeController;

    @Mock
    private MyPokeService myPokeService;

    @BeforeEach
    void setUp() {
        initMockMvc(myPokeController);
    }

    @Test
    void whenTestStarts_thenMockAndBeans_areNotNull() {
        assertNotNull(mockMvc);
        assertNotNull(myPokeController);
        assertNotNull(myPokeService);
    }

    @Test
    void givenFind5HeaviestPokemonRequest_whenFind5HeaviestPokemonRequest_thenStatusIsOk() throws Exception {
        mockMvc.perform(get(HEAVIEST_POKEMON_URL).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(myPokeService, times(1)).get5HeaviestPokemon();
    }

    @Test
    void givenFind5HighestPokemonRequest_whenFind5HighestPokemonRequest_thenStatusIsOk() throws Exception {
        mockMvc.perform(get(HIGHEST_POKEMON_URL).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(myPokeService, times(1)).get5HighestPokemon();
    }

    @Test
    void givenFind5BaseExperiencedPokemonRequest_whenFind5BaseExperiencedPokemonRequest_thenStatusIsOk() throws Exception {
        mockMvc.perform(get(BASE_EXPERIENCED_POKEMON_URL).accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(myPokeService, times(1)).get5PokemonMoreBaseXP();
    }

    private void initMockMvc(MyPokeController controller) {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
}