package com.looqbox.ls.persistence.service.imp;

import com.looqbox.ls.persistence.model.Pokemon;
import com.looqbox.ls.persistence.repository.IPokemonRepository;
import com.looqbox.ls.persistence.service.IPokemonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService implements IPokemonService {

    private IPokemonRepository pokemonResponsitory;

    public PokemonService(IPokemonRepository pokemonResponsitory) {
        this.pokemonResponsitory = pokemonResponsitory;
    }

    public List<String> findAll(Optional<String> query) {
        return pokemonResponsitory.findAll(query);
    }

    @Override
    public List<Pokemon> findAllHighlight(Optional<String> query) {
        return pokemonResponsitory.findAllHighlight(query);
    }

    @Override
    public void savePokemons(List<Pokemon> pokemonList) {
        this.pokemonResponsitory.savePokemons(pokemonList);
    }
}
