package com.alea.mypokeapi_be.client;

import com.alea.mypokeapi_be.data.entity.PokemonEntity;
import com.alea.mypokeapi_be.dto.PokemonDetailsDto;
import com.alea.mypokeapi_be.dto.PokemonResponseDto;
import com.alea.mypokeapi_be.dto.PokemonResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Alvarobf0, 23/07/2025
 */
@ExtendWith(MockitoExtension.class)
class PokeApiClientTest {

    private static final String POKEMON_RESPONSE_URL = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=1302";
    private static final String POKEMON_RESULT_URL = "https://pokeapi.co/api/v2/pokemon/1/";

    @InjectMocks
    private PokeApiClient pokeApiClient;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(pokeApiClient, "baseUrl", "https://pokeapi.co/api/v2/pokemon");
        ReflectionTestUtils.setField(pokeApiClient, "pokemonLimit", 1302);
        ReflectionTestUtils.setField(pokeApiClient, "pokemonOffset", 0);
    }

    @Test
    void givenPopulateMyPokeRepositoryCall_whenPopulateMyPokeRepository_thenReturnPokemonList() {
        List<PokemonEntity> expectedPokemonList = List.of(PokemonEntity.builder().id(1L).name("Pokemon 1").weight(10).height(10).baseExperience(10).build());

        PokemonResponseDto pokemonResponse = PokemonResponseDto.builder().results(List.of(PokemonResultDto.builder().url(POKEMON_RESULT_URL).build())).build();

        when(restTemplate.getForEntity(POKEMON_RESPONSE_URL, PokemonResponseDto.class)).thenReturn(new ResponseEntity<>(pokemonResponse, HttpStatus.OK));
        when(restTemplate.getForEntity(POKEMON_RESULT_URL, PokemonDetailsDto.class)).thenReturn(new ResponseEntity<>(
                PokemonDetailsDto
                        .builder()
                        .id(1L)
                        .name("Pokemon 1")
                        .weight(10)
                        .height(10)
                        .baseExperience(10)
                        .build(), HttpStatus.OK));

        List<PokemonEntity> pokemonList = pokeApiClient.populateMyPokeRepository();

        assertEquals(expectedPokemonList.size(), pokemonList.size());
        assertEquals(expectedPokemonList.getFirst().getId(), pokemonList.getFirst().getId());
    }

    @Test
    void givenPokemonDetailsDto_whenMapToEntity_thenEntityIsMapped() {
        PokemonDetailsDto pokemonDetails = PokemonDetailsDto.builder().id(1L).name("Pokemon 1").weight(10).height(10).baseExperience(10).build();
        PokemonEntity expectedEntity = PokemonEntity.builder().id(1L).name("Pokemon 1").weight(10).height(10).baseExperience(10).build();

        PokemonEntity resultEntity = pokeApiClient.mapToEntity(pokemonDetails);

        assertEquals(expectedEntity.getId(), resultEntity.getId());
        assertEquals(expectedEntity.getName(), resultEntity.getName());
        assertEquals(expectedEntity.getWeight(), resultEntity.getWeight());
        assertEquals(expectedEntity.getHeight(), resultEntity.getHeight());
        assertEquals(expectedEntity.getBaseExperience(), resultEntity.getBaseExperience());
    }
}