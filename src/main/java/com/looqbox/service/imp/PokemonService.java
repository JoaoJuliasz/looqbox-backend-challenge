package com.looqbox.service.imp;

import com.looqbox.persistence.model.Pokemon;
import com.looqbox.persistence.repository.IPokemonRepository;
import com.looqbox.service.IPokemonService;
import com.looqbox.sorting.SortTypes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService implements IPokemonService {

    private IPokemonRepository pokemonRepository;

    public PokemonService(IPokemonRepository pokemonResponsitory) {
        this.pokemonRepository = pokemonResponsitory;
    }

    public List<String> findAll(Optional<String> query, Optional<SortTypes> sortType) {
        return pokemonRepository.findAll(query, sortType);
    }

    @Override
    public List<Pokemon> findAllHighlight(Optional<String> query, Optional<SortTypes> sort) {
        return pokemonRepository.findAllHighlight(query, sort);
    }

    @Override
    public void savePokemons(List<Pokemon> pokemonList) {
        this.pokemonRepository.savePokemons(pokemonList);
    }
}
