package com.alea.mypokeapi_be.service;

import com.alea.mypokeapi_be.client.PokeApiClient;
import com.alea.mypokeapi_be.data.entity.PokemonEntity;
import com.alea.mypokeapi_be.repository.MyPokeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.alea.mypokeapi_be.utils.TestUtils.createPokemonDummies;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Alvarobf0, 23/07/2025
 */
@ExtendWith(MockitoExtension.class)
class MyPokeServiceImplTest {

    private List<PokemonEntity> dummyPokemon;

    @InjectMocks
    private MyPokeServiceImpl myPokeService;

    @Mock
    private MyPokeRepository myPokeRepository;

    @Mock
    private PokeApiClient pokeApiClient;

    @BeforeEach
    void setUp() {
        dummyPokemon = createPokemonDummies();
        dummyPokemon.removeFirst();
        Collections.reverse(dummyPokemon);
    }

    @Test
    void whenTestStarts_thenMockAndBeans_areNotNull() {
        assertNotNull(myPokeService);
        assertNotNull(pokeApiClient);
        assertNotNull(myPokeRepository);
    }

    @Test
    void givenApplicationStart_whenLoadPokemonData_thenRepository_isPopulated() {

        when(myPokeRepository.count()).thenReturn(0L);
        when(pokeApiClient.populateMyPokeRepository()).thenReturn(dummyPokemon);

        myPokeService.loadPokemonData();

        verify(pokeApiClient, times(1)).populateMyPokeRepository();
        verify(myPokeRepository, times(1)).saveAll(dummyPokemon);
    }

    @Test
    void givenGet5HeaviestPokemonCall_whenGet5HeaviestPokemon_thenGet5HeaviestPokemon() {
        List<PokemonEntity> heaviestPokemon = createPokemonDummies();
        heaviestPokemon.removeFirst();
        Collections.reverse(heaviestPokemon);

        when(myPokeRepository.findTop5ByOrderByWeightDesc()).thenReturn(dummyPokemon);

        assertEquals(heaviestPokemon.size(), myPokeService.get5HeaviestPokemon().size());
        assertThat(myPokeService.get5HeaviestPokemon()).allMatch(pokemon -> pokemon.getWeight() > 9);
    }

    @Test
    void givenGet5HighestPokemonCall_whenGet5HighestPokemon_thenGet5HighestPokemon() {
        List<PokemonEntity> highestPokemon = createPokemonDummies().subList(1, 6);

        when(myPokeRepository.findTop5ByOrderByHeightDesc()).thenReturn(dummyPokemon);

        assertEquals(highestPokemon.size(), myPokeService.get5HighestPokemon().size());
        assertThat(myPokeService.get5HighestPokemon()).allMatch(pokemon -> pokemon.getHeight() > 9);
    }

    @Test
    void givenGet5PokemonMoreBaseXPCall_whenGet5PokemonMoreBaseXP_thenGet5PokemonMoreBaseXP() {
        List<PokemonEntity> experiencedPokemon = createPokemonDummies().subList(1, 6);

        when(myPokeRepository.findTop5ByOrderByBaseExperienceDesc()).thenReturn(dummyPokemon);

        assertEquals(experiencedPokemon.size(), myPokeService.get5PokemonMoreBaseXP().size());
        assertThat(myPokeService.get5PokemonMoreBaseXP()).allMatch(pokemon -> pokemon.getBaseExperience() > 9);
    }
}