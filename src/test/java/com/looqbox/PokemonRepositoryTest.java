package com.looqbox;

import com.looqbox.persistence.model.dto.Pokemon;
import com.looqbox.persistence.repository.imp.PokemonRepository;
import com.looqbox.sorting.SortTypes;
import com.looqbox.sorting.imp.ListSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PokemonRepositoryTest {

    @Autowired
    private ListSort listSort;

    @Autowired
    private PokemonRepository pokemonRepository;

    @BeforeEach
    void setUp() throws IllegalAccessException, NoSuchFieldException {
        MockitoAnnotations.openMocks(this);

        Field pokemonMapField = null;
        pokemonMapField = PokemonRepository.class.getDeclaredField("pokemonMap");

        pokemonMapField.setAccessible(true);
        // Set up test data
        Map<String, Pokemon> pokemonMap = new HashMap<>();
        pokemonMap.put("pikachu", new Pokemon("pikachu"));
        pokemonMap.put("charmander", new Pokemon("charmander"));
        pokemonMap.put("articuno", new Pokemon("articuno"));

        pokemonMapField.set(pokemonRepository, pokemonMap);
    }

    @Test
    void findAllOnePokemon() {
        String query = "pi";
        SortTypes sort = SortTypes.length;
        List<String> expectedResults = Arrays.asList("pikachu");
        List<String> results = pokemonRepository.findAll(query, sort);
        assertEquals(expectedResults, results);
    }

    @Test
    void findAllMultiplePokemon() {
        String query = "ar";
        SortTypes sort = SortTypes.alphabetical;
        List<String> expectedResults = Arrays.asList("articuno", "charmander");
        List<String> results = pokemonRepository.findAll(query, sort);
        assertEquals(expectedResults, results);
    }

    @Test
    void findAllNoMatchPokemons() {
        String query = "xyz";
        SortTypes sort = SortTypes.alphabetical;
        List<String> expectedResults = Arrays.asList();
        List<String> results = pokemonRepository.findAll(query, sort);
        assertEquals(expectedResults, results);
    }

    @Test
    void findAllHighlightOnePokemon() {
        String query = "pi";
        SortTypes sort = SortTypes.length;
        Pokemon pikachu = new Pokemon("pikachu", "<pre>pi</pre>kachu");
        List<Pokemon> expectedResults = Arrays.asList(pikachu);
        List<Pokemon> results = pokemonRepository.findAllHighlight(query, sort);
        assertEquals(expectedResults, results);
    }

    @Test
    void findAllHighlightMultiplePokemons() {
        String query = "ar";
        SortTypes sort = SortTypes.alphabetical;
        Pokemon charmander = new Pokemon("charmander", "ch<pre>ar</pre>mander");
        Pokemon articuno = new Pokemon("articuno", "<pre>ar</pre>ticuno");
        List<Pokemon> expectedResults = Arrays.asList(articuno, charmander);
        List<Pokemon> results = pokemonRepository.findAllHighlight(query, sort);
        assertEquals(expectedResults, results);
    }

    @Test
    void findAllHighlightNoMatchPokemons() {
        String query = "xyz";
        SortTypes sort = SortTypes.alphabetical;
        List<Pokemon> expectedResults = Arrays.asList();
        List<Pokemon> results = pokemonRepository.findAllHighlight(query, sort);
        assertEquals(expectedResults, results);
    }
}
