package com.looqbox.ls.sorting.imp;

import com.looqbox.ls.sorting.ISort;

import java.util.ArrayList;
import java.util.List;

public class AlphabeticalSort implements ISort {
    public  <T extends Comparable<T>> void sortList(List<T> list) {
        if (list == null || list.size() <= 1) {
            return; // Base case: already sorted or empty list
        }

        mergeSort(list, 0, list.size() - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(List<T> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            merge(list, left, mid, right);
        }
    }

    private static <T extends Comparable<T>> void merge(List<T> list, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<T> leftList = new ArrayList<>(list.subList(left, left + n1));
        List<T> rightList = new ArrayList<>(list.subList(mid + 1, mid + 1 + n2));

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            T leftElement = leftList.get(i);
            T rightElement = rightList.get(j);

            if (leftElement.compareTo(rightElement) <= 0) {
                list.set(k, leftElement);
                i++;
            } else {
                list.set(k, rightElement);
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}
