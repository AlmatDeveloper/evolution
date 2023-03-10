package kz.evo.ch4;

import java.util.Arrays;

// пузырьковая сортировка
public class chapter4_bubble_sort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(bubbleSort(new int[]{1, 5, 9, 6, 5, 6, 2, 3, 4, 5, 85, 1, 5, 6, 74, 5})));
        System.out.println(Arrays.toString(bubbleSort(new int[]{7, 8, 11, 1, 5, 9})));
    }

    public static int[] bubbleSort(int[] array) {

        for (int i = 0; i < array.length - 1; ++i) {
            for (int j = 0; j < array.length - 1; ++j) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return array;
    }
}
