package com.alea.mypokeapi_be.utils;

import com.alea.mypokeapi_be.data.entity.PokemonEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alvarobf0, 23/07/2025
 */
public class TestUtils {

    public static List<PokemonEntity> createPokemonDummies() {
        List<PokemonEntity> pokemonEntities = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            PokemonEntity pokemon = PokemonEntity.builder().id(i + 1L).name("Pokemon " + i).weight(i * 10).height(i * 10).baseExperience(i * 10).build();
            pokemonEntities.add(pokemon);
        }
        return pokemonEntities;
    }
}
