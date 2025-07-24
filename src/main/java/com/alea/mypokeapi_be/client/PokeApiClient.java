package com.alea.mypokeapi_be.client;

import com.alea.mypokeapi_be.data.entity.PokemonEntity;
import com.alea.mypokeapi_be.data.dto.PokemonDetailsDto;
import com.alea.mypokeapi_be.data.dto.PokemonResponseDto;
import com.alea.mypokeapi_be.data.dto.PokemonResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alvarobf0, 22/07/2025
 */
@Slf4j
@Component
public class PokeApiClient {

    private final RestTemplate restTemplate;

    @Value("${pokeapi.pokemon-url}")
    private String baseUrl;

    @Value("${pokeapi.limit}")
    private int pokemonLimit;

    @Value("${pokeapi.offset}")
    private int pokemonOffset;

    public PokeApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PokemonEntity> populateMyPokeRepository() {
        List<PokemonEntity> pokemonList = new ArrayList<>();

        log.info("Populating pokemon data");
        ResponseEntity<PokemonResponseDto> response = restTemplate.getForEntity(baseUrl + "?offset=" + pokemonOffset + "&limit=" + pokemonLimit, PokemonResponseDto.class);
        if (response.getBody() != null) {
            PokemonResponseDto pokemonResponseDto = response.getBody();
            for (PokemonResultDto pokemonResult : pokemonResponseDto.getResults()) {
                log.info("Loading pokemon: " + pokemonResult.getUrl());
                PokemonDetailsDto pokemonDetail = restTemplate.getForEntity(pokemonResult.getUrl(), PokemonDetailsDto.class).getBody();
                if (pokemonDetail != null) {
                    PokemonEntity pokemonEntity = mapToEntity(pokemonDetail);
                    pokemonList.add(pokemonEntity);
                }
            }
        }
        return pokemonList;
    }

    public PokemonEntity mapToEntity(PokemonDetailsDto pokemonDetailsDto) {
        return PokemonEntity
                .builder()
                .id(pokemonDetailsDto.getId())
                .name(pokemonDetailsDto.getName())
                .weight(pokemonDetailsDto.getWeight())
                .height(pokemonDetailsDto.getHeight())
                .baseExperience(pokemonDetailsDto.getBaseExperience())
                .build();
    }
}
