package com.alea.mypokeapi_be.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Alvarobf0, 22/07/2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonDetailsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer weight;
    private Integer height;

    @JsonProperty("base_experience")
    private Integer baseExperience;
}
