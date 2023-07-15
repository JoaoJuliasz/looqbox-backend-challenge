package com.looqbox.ls.persistence.repository;

import com.looqbox.ls.persistence.model.Pokemon;

import java.util.List;
import java.util.Optional;

public interface IPokemonRepository {

    List<String> findAll(Optional<String> name);

    void savePokemons(List<Pokemon> pokemonList);

}
