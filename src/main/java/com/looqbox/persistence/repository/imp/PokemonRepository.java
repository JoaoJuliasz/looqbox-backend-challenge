package com.looqbox.persistence.repository.imp;

import com.looqbox.core.IPokemonFunctionalities;
import com.looqbox.persistence.model.dto.Pokemon;
import com.looqbox.sorting.SortTypes;
import com.looqbox.sorting.imp.ListSort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PokemonRepository implements IPokemonFunctionalities {

    final Map<String, Pokemon> pokemonMap = new HashMap<>();

    final ListSort listSort;

    public PokemonRepository(ListSort listSort) {
        this.listSort = listSort;
    }

    @Override
    public List<String> findAll(String query, SortTypes sort) {
        List<String> filteredPokemons = new ArrayList<>();

        for(String key: pokemonMap.keySet()) {
            if(key.contains(query)) {
                filteredPokemons.add(key);
            }
        }

        listSort.selectSort(filteredPokemons, sort);
        return filteredPokemons;
    }

    public List<Pokemon> findAllHighlight(String query, SortTypes sort) {

        List<Pokemon> filteredPokemons = new ArrayList<>();

        for(String key: pokemonMap.keySet()) {
            if(key.contains(query)) {
                if(!query.isEmpty()) {
                    enrichPokemonHighlight(pokemonMap.get(key), query);
                }
                filteredPokemons.add(pokemonMap.get(key));
            }
        }
        listSort.selectSort(filteredPokemons, sort);
        return filteredPokemons;
    }

    @Override
    public void savePokemons(List<Pokemon> pokemonList) {
        pokemonList.stream()
                .forEach(s -> pokemonMap.put(s.getName(), s));
    }

    private static void enrichPokemonHighlight(Pokemon pokemon, String query) {
        String name = pokemon.getName();
        name = name.toLowerCase().replace(query, "<pre>" + query + "</pre>");
        pokemon.setHighlight(name);
    }

}
