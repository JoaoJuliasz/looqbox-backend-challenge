package com.looqbox.ls.sorting.imp;

import com.looqbox.ls.sorting.ISort;
import com.looqbox.ls.sorting.SortTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Sort {

    private static HashMap<SortTypes, ISort> sortOptions = new HashMap<>() {{
        put(SortTypes.alphabetical, new AlphabeticalSort());
        put(SortTypes.length, new LengthSort());
    }};

    public static <T extends Comparable<T>> void selectSort(List<T> list, Optional<SortTypes> sort) {
        ISort sortOption = sortOptions.get(sort.get());
        sortOption.sortList(list);
    }

}
