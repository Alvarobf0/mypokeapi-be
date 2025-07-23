package com.alea.mypokeapi_be.service;

import com.alea.mypokeapi_be.client.PokeApiClient;
import com.alea.mypokeapi_be.data.entity.PokemonEntity;
import com.alea.mypokeapi_be.repository.MyPokeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alvarobf0, 22/07/2025
 */
@Service
public class MyPokeServiceImpl implements MyPokeService {

    private final MyPokeRepository myPokeRepository;
    private final PokeApiClient pokeApiClient;

    public MyPokeServiceImpl(MyPokeRepository myPokeRepository, PokeApiClient pokeApiClient) {
        this.myPokeRepository = myPokeRepository;
        this.pokeApiClient = pokeApiClient;
    }

    @PostConstruct
    public void loadPokemonData() {
        if (myPokeRepository.count() == 0) {
            List<PokemonEntity> pokemonEntities = pokeApiClient.populateMyPokeRepository();
            myPokeRepository.saveAll(pokemonEntities);
        }
    }

    @Override
    public List<PokemonEntity> get5HeaviestPokemon() {
        return myPokeRepository.findTop5ByOrderByWeightDesc();
    }

    @Override
    public List<PokemonEntity> get5HighestPokemon() {
        return myPokeRepository.findTop5ByOrderByHeightDesc();
    }

    @Override
    public List<PokemonEntity> get5PokemonMoreBaseXP() {
        return myPokeRepository.findTop5ByOrderByBaseExperienceDesc();
    }
}
