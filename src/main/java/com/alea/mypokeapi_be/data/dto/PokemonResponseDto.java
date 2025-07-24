package com.alea.mypokeapi_be.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alvarobf0, 22/07/2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int count;
    private String next;
    private List<PokemonResultDto> results;
}
