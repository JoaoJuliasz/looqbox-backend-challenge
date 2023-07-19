package com.looqbox.persistence.repository.imp;

import com.looqbox.persistence.model.dto.Pokemon;
import com.looqbox.persistence.repository.IPokemonRepository;
import com.looqbox.sorting.SortTypes;
import com.looqbox.sorting.imp.ListSort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PokemonRepository implements IPokemonRepository {

    final List<Pokemon> pokemons = new ArrayList<>();

    final ListSort listSort;

    public PokemonRepository(ListSort listSort) {
        this.listSort = listSort;
    }

    @Override
    public List<String> findAll(String query, SortTypes sort) {
        var streamPokemons = pokemons.stream().map(Pokemon::getName);

        if (!query.isEmpty()) {
            String lowercaseName = query.toLowerCase();
            streamPokemons = streamPokemons.filter(p -> p.toLowerCase().contains(lowercaseName));
        }

        List<String> filteredPokemons = streamPokemons.collect(Collectors.toList());
        listSort.selectSort(filteredPokemons, sort);
        return filteredPokemons;
    }

    public List<Pokemon> findAllHighlight(String query, SortTypes sort) {

        if (!query.isEmpty() && !query.equals("")) {
            String lowercaseQuery = query.toLowerCase();
            List<Pokemon> filteredPokemons = pokemons.stream()
                    .filter(p -> p.getName().toLowerCase().contains(lowercaseQuery))
                    .collect(Collectors.toList());

            filteredPokemons.forEach(p -> enrichPokemonHighlight(p, query));
            listSort.selectSort(filteredPokemons, sort);

            return filteredPokemons;
        }

        listSort.selectSort(pokemons, sort);
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
