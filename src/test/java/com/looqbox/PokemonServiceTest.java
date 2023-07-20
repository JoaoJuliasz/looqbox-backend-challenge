package com.looqbox;

import com.looqbox.persistence.model.dto.Pokemon;
import com.looqbox.core.IPokemonFunctionalities;
import com.looqbox.sorting.SortTypes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
public class PokemonServiceTest {

    @Autowired
    IPokemonFunctionalities pokemonService;

    @Test
    public void findAndLengthSortPokemonTest_thenOk() {
        List<String> foundPokemons = pokemonService.findAll("bu", SortTypes.length);
        assertThat(foundPokemons.get(0), equalTo("budew"));
    }

    @Test
    public void findAndSortPokemonTest_thenOk() {
        List<String> foundPokemons = pokemonService.findAll("bu", SortTypes.alphabetical);
        assertThat(foundPokemons.get(0), equalTo("baxcalibur"));
    }

    @Test
    public void findAndLengthSortPokemonWIthHighlightTest_thenOk() {
        List<Pokemon> foundPokemons = pokemonService.findAllHighlight("bu", SortTypes.length);
        Pokemon pokemon = new Pokemon("budew", "<pre>bu</pre>dew");
        Assertions.assertThat(foundPokemons.get(0)).isEqualTo(pokemon);
    }

    @Test
    public void findAndSortPokemonWIthHighlightTest_thenOk() {
        List<Pokemon> foundPokemons = pokemonService.findAllHighlight("bu", SortTypes.alphabetical);
        Pokemon pokemon = new Pokemon("baxcalibur", "baxcali<pre>bu</pre>r");
        Assertions.assertThat(foundPokemons.get(0)).isEqualTo(pokemon);
    }

}