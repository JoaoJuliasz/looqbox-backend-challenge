package com.looqbox.service.imp;

import com.looqbox.persistence.model.dto.Pokemon;
import com.looqbox.persistence.model.dto.PokemonRequest;
import com.looqbox.persistence.repository.IPokemonRepository;
import com.looqbox.service.IPokemonService;
import com.looqbox.sorting.SortTypes;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PokemonService implements IPokemonService {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon?limit=100000";

    private final RestTemplate restTemplate;

    private final IPokemonRepository pokemonRepository;

    public PokemonService(IPokemonRepository pokemonRepository, RestTemplateBuilder builder) {
        this.pokemonRepository = pokemonRepository;
        this.restTemplate = builder.build();
        fetchPokemons();
    }

    public List<String> findAll(String query, SortTypes sortType) {
        return pokemonRepository.findAll(query, sortType);
    }

    @Override
    public List<Pokemon> findAllHighlight(String query, SortTypes sort) {
        return pokemonRepository.findAllHighlight(query, sort);
    }

    @Cacheable("pokemons")
    @Override
    public void savePokemons(List<Pokemon> pokemonList) {
        this.pokemonRepository.savePokemons(pokemonList);
    }

    private void fetchPokemons() {
        PokemonRequest response = restTemplate.getForObject(BASE_URL, PokemonRequest.class);
        List<Pokemon> fetchedPokemons = response.getResults();
        this.savePokemons(fetchedPokemons);
    }
}
