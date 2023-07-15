package com.looqbox.ls.persistence.web;

import com.looqbox.ls.persistence.model.Pokemon;
import com.looqbox.ls.persistence.model.PokemonRequest;
import com.looqbox.ls.persistence.service.IPokemonService;
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

    private final RestTemplate restTemplate = new RestTemplate();

    IPokemonService pokemonService;

    public PokemonController(IPokemonService pokemonService) {
        this.pokemonService = pokemonService;
        getPokemons();
    }

    private void getPokemons() {
        PokemonRequest response = restTemplate.getForObject(BASE_URL, PokemonRequest.class);
        List<Pokemon> pokemons = new ArrayList<>(response.getResults());
        pokemonService.savePokemons(pokemons);
    }

    @GetMapping
    public Collection<String> findAll(@RequestParam(required = false) Optional<String> query) {
        return pokemonService.findAll(query);
    }

    @GetMapping(value = "/highlight")
    public Collection<Pokemon> findAllHighlight(@RequestParam(required = false) Optional<String> query) {
        return pokemonService.findAllHighlight(query);
    }

}
