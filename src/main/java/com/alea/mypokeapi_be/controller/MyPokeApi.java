package com.alea.mypokeapi_be.controller;

import com.alea.mypokeapi_be.data.entity.PokemonEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.alea.mypokeapi_be.constant.ApiConstant.BAD_REQUEST_CODE;
import static com.alea.mypokeapi_be.constant.ApiConstant.BAD_REQUEST_REASON;
import static com.alea.mypokeapi_be.constant.ApiConstant.INTERNAL_SERVER_ERROR_CODE;
import static com.alea.mypokeapi_be.constant.ApiConstant.INTERNAL_SERVER_ERROR_REASON;
import static com.alea.mypokeapi_be.constant.ApiConstant.OK_CODE;
import static com.alea.mypokeapi_be.constant.ApiConstant.OK_REASON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Alvarobf0, 22/07/2025
 */
@Tag(name = "MyPokeApi", description = "Personalized endpoints using PokeAPI data")
public interface MyPokeApi {

    @Operation(summary = "Gets the 5 heaviest Pokemon", description = "Returns on success the list of the 5 heaviest Pokemon found in the PokeAPI data.")
    @ApiResponse(responseCode = OK_CODE, description = OK_REASON, content = { @Content(schema = @Schema(implementation = PokemonEntity.class)) })
    @ApiResponse(responseCode = BAD_REQUEST_CODE, description = BAD_REQUEST_REASON, content = { @Content(schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = INTERNAL_SERVER_ERROR_CODE, description = INTERNAL_SERVER_ERROR_REASON, content = { @Content(schema = @Schema(implementation = String.class)) })
    @GetMapping(path = "pokemon/weight/top-5", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<PokemonEntity>> find5HeaviestPokemon();

    @Operation(summary = "Gets the 5 highest Pokemon", description = "Returns on success the list of the 5 highest Pokemon found in the PokeAPI data.")
    @ApiResponse(responseCode = OK_CODE, description = OK_REASON, content = { @Content(schema = @Schema(implementation = PokemonEntity.class)) })
    @ApiResponse(responseCode = BAD_REQUEST_CODE, description = BAD_REQUEST_REASON, content = { @Content(schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = INTERNAL_SERVER_ERROR_CODE, description = INTERNAL_SERVER_ERROR_REASON, content = { @Content(schema = @Schema(implementation = String.class)) })
    @GetMapping(path = "pokemon/height/top-5", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<PokemonEntity>> find5HighestPokemon();

    @Operation(summary = "Gets the 5 Pokemon with more base XP", description = "Returns on success the list of the 5 Pokemon with more base XP found in the PokeAPI data.")
    @ApiResponse(responseCode = OK_CODE, description = OK_REASON, content = { @Content(schema = @Schema(implementation = PokemonEntity.class)) })
    @ApiResponse(responseCode = BAD_REQUEST_CODE, description = BAD_REQUEST_REASON, content = { @Content(schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = INTERNAL_SERVER_ERROR_CODE, description = INTERNAL_SERVER_ERROR_REASON, content = { @Content(schema = @Schema(implementation = String.class)) })
    @GetMapping(path = "pokemon/base-exp/top-5", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<PokemonEntity>> find5BaseExperiencedPokemon();
}
