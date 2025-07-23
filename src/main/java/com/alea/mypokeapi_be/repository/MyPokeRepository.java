package com.alea.mypokeapi_be.repository;

import com.alea.mypokeapi_be.data.entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alvarobf0, 22/07/2025
 */
@Repository
public interface MyPokeRepository extends JpaRepository<PokemonEntity, Long> {
    List<PokemonEntity> findTop5ByOrderByWeightDesc();

    List<PokemonEntity> findTop5ByOrderByHeightDesc();

    List<PokemonEntity> findTop5ByOrderByBaseExperienceDesc();
}
