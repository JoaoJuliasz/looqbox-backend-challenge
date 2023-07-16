package com.looqbox.ls.service.imp;

import com.looqbox.ls.persistence.model.Pokemon;
import com.looqbox.ls.persistence.repository.IPokemonRepository;
import com.looqbox.ls.service.IPokemonService;
import com.looqbox.ls.sorting.SortTypes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService implements IPokemonService {

    private IPokemonRepository pokemonResponsitory;

    public PokemonService(IPokemonRepository pokemonResponsitory) {
        this.pokemonResponsitory = pokemonResponsitory;
    }

    public List<String> findAll(Optional<String> query, Optional<SortTypes> sortType) {
        return pokemonResponsitory.findAll(query, sortType);
    }

    @Override
    public List<Pokemon> findAllHighlight(Optional<String> query, Optional<SortTypes> sort) {
        return pokemonResponsitory.findAllHighlight(query, sort);
    }

    @Override
    public void savePokemons(List<Pokemon> pokemonList) {
        this.pokemonResponsitory.savePokemons(pokemonList);
    }
}
