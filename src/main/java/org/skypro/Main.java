package org.skypro;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {


        Integer[] testArray1 = new Integer[100_000];
        Random rand = new Random();
        for (int i = 0; i < testArray1.length; i++) {
            testArray1[i] = rand.nextInt(1000000);
        }

        Integer[] testArray2 = Arrays.copyOf(testArray1, testArray1.length);
        Integer[] testArray3 = Arrays.copyOf(testArray1, testArray1.length);

        long start = System.currentTimeMillis();
//        sortBubble(testArray1);
//        System.out.println((System.currentTimeMillis() - start) + " bubble");
//
//        start = System.currentTimeMillis();
        sortSelection(testArray2);
        System.out.println((System.currentTimeMillis() - start) + " selection");

        start = System.currentTimeMillis();
        sortInsertion(testArray3);
        System.out.println((System.currentTimeMillis() - start) + " insertion");


    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        Integer tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}