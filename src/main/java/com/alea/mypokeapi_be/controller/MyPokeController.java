package com.alea.mypokeapi_be.controller;

import com.alea.mypokeapi_be.data.entity.PokemonEntity;
import com.alea.mypokeapi_be.service.MyPokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Alvarobf0, 22/07/2025
 */
@RestController
@RequestMapping("/mypokeapi")
public class MyPokeController implements MyPokeApi {

    @Autowired
    private MyPokeService myPokeService;

    @Override
    public ResponseEntity<List<PokemonEntity>> find5HeaviestPokemon() {
        List<PokemonEntity> heaviestPokemon = myPokeService.get5HeaviestPokemon();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(heaviestPokemon);
    }

    @Override
    public ResponseEntity<List<PokemonEntity>> find5HighestPokemon() {
        List<PokemonEntity> highestPokemon = myPokeService.get5HighestPokemon();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(highestPokemon);
    }

    @Override
    public ResponseEntity<List<PokemonEntity>> find5BaseExperiencedPokemon() {
        List<PokemonEntity> pokemonMoreBaseXP = myPokeService.get5PokemonMoreBaseXP();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(pokemonMoreBaseXP);
    }
}
