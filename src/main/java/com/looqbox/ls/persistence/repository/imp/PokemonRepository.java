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
                .map(Pokemon::getName);

        if (name.isPresent()) {
            streamPokemons = streamPokemons.filter(p -> p.toLowerCase().contains(name.get().toLowerCase()));
        }
        filteredPokemons = streamPokemons.collect(Collectors.toList());
        return filteredPokemons;
    }

    public List<Pokemon> findAllHighlight(Optional<String> query) {

        if (query.isPresent() && !query.get().equals("")) {
            List<Pokemon> filteredPokemons;
            var streamPokemons = pokemons.stream();

            streamPokemons = streamPokemons
                    .filter(p -> p.getName().toLowerCase().contains(query.get().toLowerCase()));

            filteredPokemons = streamPokemons.collect(Collectors.toList());
            filteredPokemons.forEach(p -> enrichPokemonHighlight(p, query.get()));
            return filteredPokemons;
        }

        return pokemons;
    }

    @Override
    public void savePokemons(List<Pokemon> pokemonList) {
        pokemons.addAll(pokemonList);
    }

    private static void enrichPokemonHighlight(Pokemon pokemon, String query) {
        String name = pokemon.getName();
        name = name.toLowerCase().replace(query, "<pre>" + query + "</pre>");
        pokemon.setHighlight(name);
    }
}
