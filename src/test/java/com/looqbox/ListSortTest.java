package com.looqbox;

import com.looqbox.sorting.SortTypes;
import com.looqbox.sorting.imp.ListSort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class ListSortTest {

    @Autowired
    ListSort listSort;

    @Test
    public void sortListByLength_thenOk() {

        List<String> stringsList = Arrays.asList("Samantha", "Emily", "Ben", "Oliver");
        listSort.selectSort(stringsList, SortTypes.length);
        assertThat(stringsList, equalTo(Arrays.asList("Ben", "Emily", "Oliver", "Samantha")));
    }

    @Test
    public void sortListByAlphabetic_thenOk() {

        List<String> stringsList = Arrays.asList("Patrick", "Aiden", "Jackson", "Danielle");
        listSort.selectSort(stringsList, SortTypes.alphabetical);
        assertThat(stringsList, equalTo(Arrays.asList("Aiden", "Danielle", "Jackson", "Patrick")));
    }

}
