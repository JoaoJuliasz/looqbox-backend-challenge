package com.looqbox.sorting.imp;

import com.looqbox.sorting.ISort;
import com.looqbox.sorting.SortTypes;

import java.util.List;
import java.util.Optional;

public class Sort {

    public static <T extends Comparable<T>> void selectSort(List<T> list, Optional<SortTypes> sort) {
        ISort sorter = new ListSort();
        sorter.sortList(list, sort.get() == SortTypes.length);
    }

}
