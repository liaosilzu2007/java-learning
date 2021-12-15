package com.lzumetal.javalearn.algorithm;

import com.google.gson.Gson;
import org.junit.Test;


/**
 * @author liaosi
 * @date 2021-12-14
 */
public class DpAlgoTest {

    public static final Gson GSON = new Gson();


    @Test
    public void getMaxValueTest() {

    }


    /**
     * 求最大价值
     *
     * @param arrs
     * @param nums
     */
    public int[] getMaxValue(int[] arrs, int[] nums) {
        int n = arrs.length;
        int[] result = new int[n];
        if (arrs.length == 1) {
            return arrs;
        }
        long[][] values = new long[n][n];
        for (int num : nums) {
            for (int i = 0; i < arrs.length; i++) {
                if (i == 0) {
                }
            }
        }
        return result;
    }


    @Test
    public void testJump() {
        System.out.println(jump(1));
        System.out.println(jump(2));
        System.out.println(jump(3));
        System.out.println(jump(10));
    }


    /**
     * 问题描述：一只青蛙一次可以跳上1级台阶，也可以跳上2级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
     */
    private int jump(int n) {
        int[] count = new int[n];
        //初始化
        if (n >= 1) {
            count[0] = 1;
        }
        if (n >= 2) {
            count[1] = 2;
        }
        //转移方程
        for (int i = 3; i <= n; i++) {
            int index = i - 1;
            count[index] = count[index - 1] + count[index - 2];
        }
        return count[count.length - 1];
    }


    @Test
    public void testUniquePathCount() {
        System.out.println(uniquePathCount(3, 2));
        System.out.println(uniquePathCount(7, 3));
        System.out.println(uniquePathCount(3, 3));
    }


    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     *
     * @return
     */
    private int uniquePathCount(int rows, int columns) {
        int[][] count = new int[rows][columns];
        // 初始化
        for (int i = 0; i < columns; i++) {
            count[0][i] = 1;
        }
        for (int i = 0; i < rows; i++) {
            count[i][0] = 1;
        }
        //转移方程
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                count[i][j] = count[i - 1][j] + count[i][j - 1];
            }
        }
        return count[rows - 1][columns - 1];
    }


    @Test
    public void minPathSumTest() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
        int[][] grid2 = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum(grid2));
    }


    /**
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     */
    private int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] sum = new int[rows][columns];
        // 初始化
        sum[0][0] = grid[0][0];
        for (int i = 1; i < columns; i++) {
            sum[0][i] = grid[0][i] + sum[0][i - 1];
        }
        for (int i = 1; i < rows; i++) {
            sum[i][0] = grid[i][0] + sum[i - 1][0];
        }
        //转移方程
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                sum[i][j] = Math.min(sum[i - 1][j] + grid[i][j], sum[i][j - 1] + grid[i][j]);
            }
        }
        return sum[rows - 1][columns - 1];
    }


}
