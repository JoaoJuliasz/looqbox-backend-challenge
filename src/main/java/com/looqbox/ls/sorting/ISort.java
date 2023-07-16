package com.looqbox.ls.sorting;

import java.util.List;

public interface ISort {

    <T extends Comparable<T>> void sortList(List<T> list);

     static <T extends Comparable<T>> void mergeSort(List<T> list, int left, int right) {}

    static <T extends Comparable<T>> void merge(List<T> list, int left, int mid, int right) {}
}
