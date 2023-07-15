package com.looqbox.ls.persistence.service;

import com.looqbox.ls.persistence.model.Pokemon;

import java.util.List;
import java.util.Optional;

public interface IPokemonService {

    List<String> findAll(Optional<String> query);

    List<Pokemon> findAllHighlight(Optional<String> query);

    void savePokemons(List<Pokemon> pokemonList);

}
