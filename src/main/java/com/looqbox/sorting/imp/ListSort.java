package com.looqbox.sorting.imp;

import com.looqbox.persistence.model.dto.Pokemon;
import com.looqbox.sorting.SortTypes;

import java.util.ArrayList;
import java.util.List;

public class ListSort {

    public <T extends Comparable<T>> void selectSort(List<T> list, SortTypes sort) {
        sortList(list, sort.name().equalsIgnoreCase(SortTypes.length.name()));
    }

    //The sort method chosen is merge sort
    //It is O(n log n)
    private <T extends Comparable<T>> void sortList(List<T> list, boolean sortByLength) {
        if (list == null || list.size() <= 1) {
            return; // Base case: already sorted or empty list
        }

        mergeSort(list, 0, list.size() - 1, sortByLength);
    }

    //Divide array into sublists, until they have only one element
    //After it, merge the sublists, producing one sorted array
    private <T extends Comparable<T>> void mergeSort(List<T> list, int left, int right, boolean sortByLength) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(list, left, mid, sortByLength);
            mergeSort(list, mid + 1, right, sortByLength);

            merge(list, left, mid, right, sortByLength);
        }
    }

    private <T extends Comparable<T>> void merge(List<T> list, int leftIndex, int midIndex, int rightIndex, boolean sortByLength) {
        // Create left and right sublists
        List<T> leftSubList = new ArrayList<>(list.subList(leftIndex, midIndex + 1));
        List<T> rightSubList = new ArrayList<>(list.subList(midIndex + 1, rightIndex + 1));

        // Initialize pointers for left and right sublists
        int leftPointer = 0, rightPointer = 0;

        // Merge the sublists into the original list in sorted order
        for (int sortedIndex = leftIndex; sortedIndex <= rightIndex; sortedIndex++) {
            if (rightPointer >= rightSubList.size() || (leftPointer < leftSubList.size() &&
                    isFirstElementPreferred(leftSubList.get(leftPointer), rightSubList.get(rightPointer), sortByLength))) {
                list.set(sortedIndex, leftSubList.get(leftPointer++));
            } else {
                list.set(sortedIndex, rightSubList.get(rightPointer++));
            }
        }
    }

    //Comparing both firstElement and secondElement. We have comparison for alphabetical and length sort
    private static <T extends Comparable<T>> boolean isFirstElementPreferred(T firstElement, T secondElement, boolean sortByLength) {
        if(sortByLength) {
            return getLength(firstElement) <= getLength(secondElement);
        }
        return firstElement.compareTo(secondElement) <= 0;
    }

    private static int getLength(Object element) {
        if (element instanceof String) {
            return ((String) element).length();
        } else if (element instanceof Pokemon) {
            return ((Pokemon) element).getName().length();
        }
        throw new IllegalArgumentException("Unsupported type");
    }
}
