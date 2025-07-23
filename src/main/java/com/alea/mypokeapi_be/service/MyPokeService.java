package com.alea.mypokeapi_be.service;

import com.alea.mypokeapi_be.data.entity.PokemonEntity;

import java.util.List;

/**
 * @author Alvarobf0, 22/07/2025
 */
public interface MyPokeService {

    List<PokemonEntity> get5HeaviestPokemon();

    List<PokemonEntity> get5HighestPokemon();

    List<PokemonEntity> get5PokemonMoreBaseXP();
}
