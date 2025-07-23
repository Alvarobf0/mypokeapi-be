package com.alea.mypokeapi_be.repository;

import com.alea.mypokeapi_be.data.entity.PokemonEntity;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alvarobf0, 23/07/2025
 */
@SpringBootTest
class MyPokeRepositoryIT {

    @Resource
    MyPokeRepository myPokeRepository;

    @Test
    void givenPokeApiData_whenFindTop5ByOrderByWeightDesc_thenGet5HeaviestPokemon() {
        List<PokemonEntity> heaviestPokemon = myPokeRepository.findTop5ByOrderByWeightDesc();
        assertEquals(5, heaviestPokemon.size());
        assertThat(heaviestPokemon).allMatch(pokemon -> pokemon.getWeight() >= 10000);
    }

    @Test
    void givenPokeApiData_whenFindTop5ByOrderByHeightDesc_thenGet5HighestPokemon() {
        List<PokemonEntity> highestPokemon = myPokeRepository.findTop5ByOrderByHeightDesc();
        assertEquals(5, highestPokemon.size());
        assertThat(highestPokemon).allMatch(pokemon -> pokemon.getHeight() >= 400);
    }

    @Test
    void givenPokeApiData_whenFindTop5ByBaseExperienceDesc_thenGet5BaseExperiencedPokemon() {
        List<PokemonEntity> experiencedPokemon = myPokeRepository.findTop5ByOrderByBaseExperienceDesc();
        assertEquals(5, experiencedPokemon.size());
        assertThat(experiencedPokemon).allMatch(pokemon -> pokemon.getBaseExperience() >= 390);
    }
}