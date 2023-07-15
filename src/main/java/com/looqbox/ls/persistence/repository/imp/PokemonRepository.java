package com.looqbox.ls.persistence.repository.imp;

import com.looqbox.ls.persistence.model.Pokemon;
import com.looqbox.ls.persistence.repository.IPokemonRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PokemonRepository implements IPokemonRepository {

    List<Pokemon> pokemons = new ArrayList<>();

    @Override
    public List<String> findAll(Optional<String> name) {
        List<String> filteredPokemons;

        var streamPokemons = pokemons.stream()
                .map(p -> p.getName());

        if (name.isPresent()) {
            streamPokemons = streamPokemons.filter(p -> p.toLowerCase().contains(name.get().toLowerCase()));
        }
        filteredPokemons = streamPokemons.collect(Collectors.toList());
        return filteredPokemons;
    }

    @Override
    public void savePokemons(List<Pokemon> pokemonList) {
        pokemonList.forEach(p -> pokemons.add(p));
    }
}
