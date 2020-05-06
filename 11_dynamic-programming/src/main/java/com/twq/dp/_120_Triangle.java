package com.twq.dp;

import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * 链接：https://leetcode-cn.com/problems/triangle
 */
public class _120_Triangle {
    // 自顶向下动态规划
    public int minimumTotal1(List<List<Integer>> triangle) {
        // 1. 状态定义：dp[i][j] 表示经过第 i 行第 j 列的最小路径和
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[][] dp = new int[m][n];

        // 2. 状态初始化
        dp[0][0] = triangle.get(0).get(0);

        // 3. 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) { // 最左端的元素
                    // 只能从 triangle[i-1][j] 经过
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == i) { // 最右端的元素
                    // 只能从 triangle[i-1][j-1] 经过
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    // 从上面两个元素最小路径和的那个元素经过
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }

        // 4. 返回结果
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // dp 的最后一行记录题目要求的最小路径和
            res = Math.min(res, dp[m - 1][i]);
        }
        return res;
    }

    // 自顶向下动态规划 (空间优化)
    // 观察自顶向下的代码会发现:
    // 对第 i 行的最小路径和的推导，只需要第 i - 1 的 dp[i - 1][j] 和 dp[i - 1][j - 1] 元素即可
    // 所以可以使用两个临时变量暂存
    public int minimumTotal2(List<List<Integer>> triangle) {
        // 1. 状态定义：dp[i] 表示经过第 i 行的最小路径和
        int m = triangle.size();
        int[] dp = new int[m];

        // 2. 状态初始化
        dp[0] = triangle.get(0).get(0);

        // 3. 状态转移
        // prev 暂存 dp[i - 1][j - 1], curr 暂存 dp[i - 1][j]
        int prev = 0, curr;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                curr = dp[j];
                if (j == 0) { // 最左端的元素
                    // 只能从 triangle[i-1][j] 经过
                    dp[j] = curr + triangle.get(i).get(j);
                } else if (j == i) { // 最右端的元素
                    // 只能从 triangle[i-1][j-1] 经过
                    dp[j] = prev + triangle.get(i).get(j);
                } else {
                    // 从上面两个元素最小路径和的那个元素经过
                    dp[j] = Math.min(curr, prev) + triangle.get(i).get(j);
                }
                prev = curr;
            }
        }

        // 4. 返回结果
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }

    // 自底向上动态规划
    public int minimumTotal3(List<List<Integer>> triangle) {
        // 1. 状态定义：dp[i][j] 表示经过第 i 行第 j 列的最小路径和
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[][] dp = new int[m][n];

        // 2. 状态初始化：dp 最后一行等于 triangle 最后一行
        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = triangle.get(m - 1).get(i);
        }

        // 3. 状态转移
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }

        // 4. 返回结果
        return dp[0][0];
    }

    // 自底向上动态规划 （空间优化）
    // 因为求第 i 行的最小路径和，只需要知道第 i + 1 行的最小路径和就可以
    public int minimumTotal4(List<List<Integer>> triangle) {
        // 1. 状态定义：dp[i] 表示求第 i 行时，第 i+1 的最小路径和
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[] dp = new int[m];

        // 2. 状态初始化：dp 等于 triangle 最后一行
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(m - 1).get(i);
        }

        // 3. 状态转移
        for (int i = m - 2; i >= 0; i--) {
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j < rows.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }

        // 4. 返回结果
        return dp[0];
    }
}
