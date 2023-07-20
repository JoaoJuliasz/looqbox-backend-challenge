package com.looqbox;

import com.looqbox.persistence.model.dto.Pokemon;
import com.looqbox.persistence.repository.imp.PokemonRepository;
import com.looqbox.sorting.SortTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PokemonRepositoryTest {

    private final PokemonRepository pokemonRepository = new PokemonRepository();

    @BeforeAll
    public void setUp() {
        List<Pokemon> pokemonsList = Arrays.asList(
                new Pokemon("pikachu"),
                new Pokemon("charmander"),
                new Pokemon("articuno")
        );

        pokemonRepository.savePokemons(pokemonsList);
    }

    @Test
    public void findAllOnePokemon() {
        String query = "pi";
        SortTypes sort = SortTypes.length;
        List<String> expectedResults = Collections.singletonList("pikachu");
        List<String> results = pokemonRepository.findAll(query, sort);
        assertEquals(expectedResults, results);
    }

    @Test
    public void findAllMultiplePokemon() {
        String query = "ar";
        SortTypes sort = SortTypes.alphabetical;
        List<String> expectedResults = Arrays.asList("articuno", "charmander");
        List<String> results = pokemonRepository.findAll(query, sort);
        assertEquals(expectedResults, results);
    }

    @Test
    public void findAllNoMatchPokemons() {
        String query = "xyz";
        SortTypes sort = SortTypes.alphabetical;
        List<String> expectedResults = Arrays.asList();
        List<String> results = pokemonRepository.findAll(query, sort);
        assertEquals(expectedResults, results);
    }

    @Test
    public void findAllHighlightOnePokemon() {
        String query = "pi";
        SortTypes sort = SortTypes.length;
        Pokemon pikachu = new Pokemon("pikachu", "<pre>pi</pre>kachu");
        List<Pokemon> expectedResults = Arrays.asList(pikachu);
        List<Pokemon> results = pokemonRepository.findAllHighlight(query, sort);
        assertEquals(expectedResults, results);
    }

    @Test
    public void findAllHighlightMultiplePokemons() {
        String query = "ar";
        SortTypes sort = SortTypes.alphabetical;
        Pokemon charmander = new Pokemon("charmander", "ch<pre>ar</pre>mander");
        Pokemon articuno = new Pokemon("articuno", "<pre>ar</pre>ticuno");
        List<Pokemon> expectedResults = Arrays.asList(articuno, charmander);
        List<Pokemon> results = pokemonRepository.findAllHighlight(query, sort);
        assertEquals(expectedResults, results);
    }

    @Test
    public void findAllHighlightNoMatchPokemons() {
        String query = "xyz";
        SortTypes sort = SortTypes.alphabetical;
        List<Pokemon> expectedResults = Arrays.asList();
        List<Pokemon> results = pokemonRepository.findAllHighlight(query, sort);
        assertEquals(expectedResults, results);
    }
}
