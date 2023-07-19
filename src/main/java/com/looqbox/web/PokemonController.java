package com.looqbox.web;

import com.looqbox.persistence.model.dto.Pokemon;
import com.looqbox.service.IPokemonService;
import com.looqbox.sorting.SortTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {

    private final IPokemonService pokemonService;

    public PokemonController(IPokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<String>>> findAll(
            @RequestParam(required = false, defaultValue = "") String query,
            @RequestParam(required = false, defaultValue = "alphabetical") SortTypes sort) {

        List<String> foundPokemons = pokemonService.findAll(query, sort);

        if (foundPokemons.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return convertToResponse(foundPokemons);
    }

    @GetMapping(value = "/highlight")
    public ResponseEntity<Map<String, List<Pokemon>>> findAllHighlight(
            @RequestParam(required = false, defaultValue = "") String query,
            @RequestParam(name = "sort", required = false, defaultValue = "alphabetical") SortTypes sort) {

        List<Pokemon> foundPokemons = pokemonService.findAllHighlight(query, sort);

        if (foundPokemons.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return convertToResponse(foundPokemons);
    }

    private static <T> ResponseEntity<Map<String, List<T>>> convertToResponse(List<T> foundPokemons) {
        Map<String, List<T>> responsePokemon = new HashMap<>() {{
            put("results", foundPokemons);
        }};
        return ResponseEntity.ok(responsePokemon);
    }
}
