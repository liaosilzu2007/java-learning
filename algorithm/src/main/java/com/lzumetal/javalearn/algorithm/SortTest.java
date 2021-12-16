package com.lzumetal.javalearn.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author liaosi
 * @date 2021-12-15
 */
@Slf4j
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


    @Test
    public void mergeSortTest() {
        System.out.println(Arrays.toString(mergeSort(new int[]{3, 1, 8, 6, 5, 7, 3, 2, 0})));
    }


    public int[] mergeSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return arr;
        }
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }


    private void mergeSort(int[] arr, int l, int r) {
        if (r > l) {
            int mid = (l + r) / 2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            sort(arr, l, mid, r);
        }
    }


    public void sort(int[] arr, int l, int s, int r) {
        int length = r - l + 1;
        int[] temp = new int[length];
        int i = l;
        int j = s + 1;
        for (int n = 0; n < length; n++) {
            if (i > s && j <= r) { //全部取右边的序列
                temp[n] = arr[j++];
                continue;
            }
            if (j > r && i <= s) { //全部取左边的序列
                temp[n] = arr[i++];
                continue;
            }
            if (i <= s && j <= r) { //左右两边序列取最小的值
                temp[n] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
            }
        }
        for (int k = l; k <= r; k++) {
            arr[k] = temp[k - l];
        }
    }


    private void mergeSortV2(int[] arr, int l, int r) {
        if (l <= r) {
            return;
        }
        //这里为什么不写成 int mid = (l + r) / 2，因为如果r值很大的话可能导致 l+r 的值溢出整型范围。
        //所以严谨的写法是 int mid =  l + (l + r) / 2，因为除2的操作可以用右移一位，所以也可以写成 int mid = l + (r - l) >> 2
        int mid = l + (r - l) >> 1;
        mergeSortV2(arr, l, mid);
        mergeSortV2(arr, mid + 1, r);
        sortV2(arr, l, mid, r);
    }

    public void sortV2(int[] arr, int l, int s, int r) {
        int length = r - l + 1;
        int[] temp = new int[length];
        int n = 0;
        int i = l;
        int j = s + 1;
        while (i <= s && j <= r) {
            temp[n++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= s) {
            temp[n++] = arr[i++];
        }
        while (j <= r) {
            temp[n++] = arr[j++];
        }
        for (int k = 0; k < length; k++) {
            arr[l + k] = temp[k];
        }
    }


}
