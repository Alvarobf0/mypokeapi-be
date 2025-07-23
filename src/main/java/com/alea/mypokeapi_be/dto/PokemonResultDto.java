package com.alea.mypokeapi_be.dto;

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
public class PokemonResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String url;
}
