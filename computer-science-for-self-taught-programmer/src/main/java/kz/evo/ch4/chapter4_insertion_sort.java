package kz.evo.ch4;

import java.util.Arrays;

// сортировка вставками
public class chapter4_insertion_sort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(insertionSort(new int[]{4, 5, 56, 74, 4, 5, 64, 45, 5, 4, 64, 54})));
    }

    private static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            int value = array[i];
            while (i > 0 && value < array[i - 1]) {
                array[i] = array[i - 1];
                i = i - 1;
            }
            array[i] = value;
        }
        return array;
    }
}
