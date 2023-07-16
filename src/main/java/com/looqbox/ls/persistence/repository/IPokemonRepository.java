package com.looqbox.ls.persistence.repository;

import com.looqbox.ls.persistence.model.Pokemon;
import com.looqbox.ls.sorting.SortTypes;

import java.util.List;
import java.util.Optional;

public interface IPokemonRepository {

    List<String> findAll(Optional<String> query, Optional<SortTypes> sort);

    List<Pokemon> findAllHighlight(Optional<String> query, Optional<SortTypes> sort);

    void savePokemons(List<Pokemon> pokemonList);

}
