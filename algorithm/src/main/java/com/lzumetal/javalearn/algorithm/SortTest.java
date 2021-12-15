package com.lzumetal.javalearn.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author liaosi
 * @date 2021-12-15
 */
public class SortTest {

    /**
     * 交换数组元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    @Test
    public void selectSortTest() {
        System.out.println(Arrays.toString(selectSort(new int[]{49, 38, 65, 97, 76, 13, 27, 49})));
    }


    /**
     * 选择排序
     *
     * @param arr
     * @return
     */
    public int[] selectSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return arr;
        }
        int length = arr.length;
        int min;
        for (int i = 0; i < length - 1; i++) { //外层只需要循环 length-1 次
            min = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[i]) {
                    min = j;
                }
            }
            if (min > i) {
                swap(arr, i, min);
            }
        }
        return arr;
    }

    @Test
    public void bubbleSortTest() {
        System.out.println(Arrays.toString(bubbleSort(new int[]{49, 38, 65, 97, 76, 13, 27, 49})));
    }

    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return arr;
        }
        int length = arr.length;
        boolean flag;
        for (int i = 0; i < length - 1; i++) {
            flag = true;//设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成。
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }


    @Test
    public void insertSortTest() {
        System.out.println(Arrays.toString(insertSort(new int[]{5, 2, 5, 4, 6, 1, 3})));
    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public int[] insertSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return arr;
        }
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
        return arr;
    }


}
