package com.looqbox.service.imp;

import com.looqbox.persistence.model.Pokemon;
import com.looqbox.persistence.model.PokemonRequest;
import com.looqbox.persistence.repository.IPokemonRepository;
import com.looqbox.service.IPokemonService;
import com.looqbox.sorting.SortTypes;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService implements IPokemonService {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon?limit=100000";

    private final RestTemplate restTemplate;

    private IPokemonRepository pokemonRepository;

    public PokemonService(IPokemonRepository pokemonRepository, RestTemplateBuilder builder) {
        this.pokemonRepository = pokemonRepository;
        this.restTemplate = builder.build();
        fetchPokemons();
    }

    @Cacheable("pokemons")
    public List<String> findAll(Optional<String> query, Optional<SortTypes> sortType) {
        return pokemonRepository.findAll(query, sortType);
    }

    @Cacheable("pokemons-highlight")
    @Override
    public List<Pokemon> findAllHighlight(Optional<String> query, Optional<SortTypes> sort) {
        return pokemonRepository.findAllHighlight(query, sort);
    }

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
