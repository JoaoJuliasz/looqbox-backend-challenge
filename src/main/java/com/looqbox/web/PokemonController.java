package com.looqbox.web;

import com.looqbox.persistence.model.Pokemon;
import com.looqbox.service.IPokemonService;
import com.looqbox.sorting.SortTypes;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {

    IPokemonService pokemonService;

    public PokemonController(IPokemonService pokemonService, RestTemplateBuilder builder) {
        this.pokemonService = pokemonService;
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
