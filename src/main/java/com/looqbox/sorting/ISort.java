package com.looqbox.sorting;

import java.util.List;

public interface ISort {

    <T extends Comparable<T>> void sortList(List<T> list, boolean sortByLength);

    static <T extends Comparable<T>> void mergeSort(List<T> list, int left, int right, boolean sortByLength) {}

    static <T extends Comparable<T>> void merge(List<T> list, int left, int mid, int right, boolean sortByLength) {}
}
