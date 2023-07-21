package com.looqbox;

import com.looqbox.persistence.model.dto.Pokemon;
import com.looqbox.sorting.SortTypes;
import com.looqbox.sorting.imp.ListSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListSortTest {

    ListSort listSort = new ListSort();

    @Test
    public void sortStringsListByLength_thenOk() {

        List<String> stringsList = Arrays.asList("Samantha", "Emily", "Ben", "Oliver");
        listSort.selectSort(stringsList, SortTypes.length);
        assertThat(stringsList, equalTo(Arrays.asList("Ben", "Emily", "Oliver", "Samantha")));
    }

    @Test
    public void sortPokemonsListByLength_thenOk() {

        Pokemon pikachu = new Pokemon("pikachu");
        Pokemon charmander = new Pokemon("charmander");
        Pokemon articuno = new Pokemon("articuno");

        List<Pokemon> stringsList = Arrays.asList(
                pikachu, charmander, articuno
        );
        listSort.selectSort(stringsList, SortTypes.length);
        assertThat(stringsList, equalTo(Arrays.asList(pikachu, articuno, charmander)));
    }

    @Test
    public void sortStringsListByAlphabetic_thenOk() {

        List<String> stringsList = Arrays.asList("Patrick", "Aiden", "Jackson", "Danielle");
        listSort.selectSort(stringsList, SortTypes.alphabetical);
        assertThat(stringsList, equalTo(Arrays.asList("Aiden", "Danielle", "Jackson", "Patrick")));
    }

    @Test
    public void sortPokemonsListByAlphabetic_thenOk() {

        Pokemon pikachu = new Pokemon("pikachu");
        Pokemon charmander = new Pokemon("charmander");
        Pokemon articuno = new Pokemon("articuno");

        List<Pokemon> stringsList = Arrays.asList(
                pikachu, charmander, articuno
        );
        listSort.selectSort(stringsList, SortTypes.alphabetical);
        assertThat(stringsList, equalTo(Arrays.asList(articuno, charmander, pikachu)));
    }

}
