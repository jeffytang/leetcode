package com.twq.dp;

/**
 * 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 */
public class _64_MinimumPathSum {
    // 暴力解法 （超时）
    public int minPathSum1(int[][] grid) {
        return calculate(grid, 0, 0);
    }

    private int calculate(int[][] grid, int i, int j) {
        // 超出了矩阵的范围，所以走不到
        if (i == grid.length || j == grid[0].length)
            return Integer.MAX_VALUE;

        // 已经到了右下角
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return grid[i][j];

        return grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
    }

    // 二维动态规划 (自底而上)
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 状态定义：dp[i][j] 表示从坐标 (i, j) 到左下角的最短路径和
        int[][] dp = new int[m][n];

        // 状态初始化
        dp[m - 1][n - 1] = grid[m - 1][n - 1];

        // 状态转移
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) { // 最后一行
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (i != m - 1 && j == n - 1) { // 最后一列
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else if (i != m - 1 && j != n - 1) { // 中间的元素
                    dp[i][j] = grid[i][j] + Math.min(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        // 返回结果
        return dp[0][0];
    }

    // 一维动态规划 (自底而上)
    public int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 状态定义：dp[i] 表示从求第 i 行时，第 i + 1 行到左下角的最短路径和
        int[] dp = new int[n];

        // 状态初始化
        dp[n - 1] = grid[m - 1][n - 1];

        // 状态转移
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) { // 最后一行
                    dp[j] = grid[i][j] + dp[j + 1];
                } else if (i != m - 1 && j == n - 1) { // 最后一列
                    dp[j] = grid[i][j] + dp[j];
                } else if (i != m - 1 && j != n - 1) { // 中间的元素
                    dp[j] = grid[i][j] + Math.min(dp[j + 1], dp[j]);
                }
            }
        }

        // 返回结果
        return dp[0];
    }

    // 二维动态规划 (自底而上) (不需要额外存储空间)
    // 使用原始数组作为动态规划数组
    public int minPathSum4(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 状态转移
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) { // 最后一行
                    grid[i][j] = grid[i][j] + grid[i][j + 1];
                } else if (i != m - 1 && j == n - 1) { // 最后一列
                    grid[i][j] = grid[i][j] + grid[i + 1][j];
                } else if (i != m - 1 && j != n - 1) { // 中间的元素
                    grid[i][j] = grid[i][j] + Math.min(grid[i][j + 1], grid[i + 1][j]);
                }
            }
        }

        // 返回结果
        return grid[0][0];
    }

    // 二维动态规划 (自上到底) (不需要额外存储空间)
    // 使用原始数组作为动态规划数组
    public int minPathSum5(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 状态转移
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j != 0) { // 第一行
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (i != 0 && j == 0) { // 第一列
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else if (i != 0 && j != 0) { // 中间的元素
                    grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }

        // 返回结果
        return grid[m - 1][n - 1];
    }
}
