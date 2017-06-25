package com.wenjian;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int [] a = new int[100];
        for (int i=0;i<100;i++) {
            a[i] = (int) (Math.random() * 1000);
        }

        System.out.println(Arrays.toString(a));
        bubbleSort(a);
        System.out.println("**************************************");
        System.out.println(Arrays.toString(a));
        System.out.println("**************************************");
        reverse(a, 0, 99);
        System.out.println(Arrays.toString(a));
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (boolean sorted = false; sorted = !sorted; n--) {
            for (int j = 1; j < n; j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j, j - 1);
                    sorted = false;
                }
            }
        }
    }

    private static void swap(int[] array, int j, int i) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }


    public static void reverse(int[] a, int lo, int hi) {
        if (lo < hi) {
            swap(a, lo, hi);
            reverse(a, lo + 1, hi - 1);
        }
    }

}
