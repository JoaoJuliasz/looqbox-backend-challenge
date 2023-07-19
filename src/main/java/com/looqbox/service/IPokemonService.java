package com.looqbox.service;

import com.looqbox.persistence.model.dto.Pokemon;
import com.looqbox.sorting.SortTypes;

import java.util.List;

public interface IPokemonService {

    List<String> findAll(String query, SortTypes sort);

    List<Pokemon> findAllHighlight(String query, SortTypes sort);

    void savePokemons(List<Pokemon> pokemonList);

}
