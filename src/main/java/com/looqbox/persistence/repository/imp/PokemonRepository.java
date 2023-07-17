package com.looqbox.persistence.repository.imp;

import com.looqbox.persistence.model.Pokemon;
import com.looqbox.persistence.repository.IPokemonRepository;
import com.looqbox.sorting.SortTypes;
import com.looqbox.sorting.imp.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PokemonRepository implements IPokemonRepository {

    List<Pokemon> pokemons = new ArrayList<>();

    @Override
    public List<String> findAll(Optional<String> name, Optional<SortTypes> sort) {
        var streamPokemons = pokemons.stream().map(Pokemon::getName);

        if (name.isPresent()) {
            String lowercaseName = name.get().toLowerCase();
            streamPokemons = streamPokemons.filter(p -> p.toLowerCase().contains(lowercaseName));
        }

        List<String> filteredPokemons = streamPokemons.collect(Collectors.toList());
        Sort.selectSort(filteredPokemons, sort);
        return filteredPokemons;
    }

    public List<Pokemon> findAllHighlight(Optional<String> query, Optional<SortTypes> sort) {

        if (query.isPresent() && !query.get().equals("")) {
            String lowercaseQuery = query.get().toLowerCase();
            List<Pokemon> filteredPokemons = pokemons.stream()
                    .filter(p -> p.getName().toLowerCase().contains(lowercaseQuery))
                    .collect(Collectors.toList());

            filteredPokemons.forEach(p -> enrichPokemonHighlight(p, query.get()));
            Sort.selectSort(filteredPokemons, sort);

            return filteredPokemons;
        }

        Sort.selectSort(pokemons, sort);
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
