package com.looqbox.web;

import com.looqbox.persistence.model.Pokemon;
import com.looqbox.service.IPokemonService;
import com.looqbox.sorting.SortTypes;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {

    IPokemonService pokemonService;

    public PokemonController(IPokemonService pokemonService, RestTemplateBuilder builder) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public Collection<String> findAll(@RequestParam(required = false) Optional<String> query, @RequestParam(required = false, defaultValue = "alphabetical") Optional<SortTypes> sort) {

        List<String> foundPokemons = pokemonService.findAll(query, sort);

        if (foundPokemons.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return foundPokemons;
    }

    @GetMapping(value = "/highlight")
    public Collection<Pokemon> findAllHighlight(
            @RequestParam(required = false) Optional<String> query,
            @RequestParam(name = "sort", required = false, defaultValue = "alphabetical") Optional<SortTypes> sort) {

        List<Pokemon> foundPokemons = pokemonService.findAllHighlight(query, sort);

        if (foundPokemons.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return foundPokemons;
    }
}
