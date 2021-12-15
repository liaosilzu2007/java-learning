package com.lzumetal.javalearn.algorithm.easy;

import org.junit.Test;

import java.util.List;

/**
 * @author liaosi
 * @date 2021-12-15
 */
public class ArrayTest {

    @Test
    public void removeDuplicatesTest() {
        System.out.println(removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }


    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * 注：不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     */
    private int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int e = nums[0];
        int p = 0;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > e) {
                nums[++p] = num;
                e = nums[p];
            }
        }
        return p + 1;
    }




}
