package com.looqbox.web;

import com.looqbox.persistence.model.Pokemon;
import com.looqbox.persistence.model.PokemonRequest;
import com.looqbox.service.IPokemonService;
import com.looqbox.sorting.SortTypes;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon?limit=100000";

    IPokemonService pokemonService;

    private final RestTemplate restTemplate;

    public PokemonController(IPokemonService pokemonService, RestTemplateBuilder builder) {
        this.pokemonService = pokemonService;
        this.restTemplate = builder.build();
        getPokemons();
    }

    private void getPokemons() {
        PokemonRequest response = restTemplate.getForObject(BASE_URL, PokemonRequest.class);
        List<Pokemon> pokemons = new ArrayList<>(response.getResults());
        pokemonService.savePokemons(pokemons);
    }

    @GetMapping
    public Collection<String> findAll(@RequestParam(required = false) Optional<String> query, @RequestParam(required = false) Optional<SortTypes> sort) {
        return pokemonService.findAll(query, sort);
    }

    @GetMapping(value = "/highlight")
    public Collection<Pokemon> findAllHighlight(
            @RequestParam(required = false) Optional<String> query,
            @RequestParam(name = "sort", required = false, defaultValue = "alphabetical") Optional<SortTypes> sort) {
        return pokemonService.findAllHighlight(query, sort);
    }

}
