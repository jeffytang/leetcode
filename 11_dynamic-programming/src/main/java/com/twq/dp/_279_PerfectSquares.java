package com.twq.dp;

import java.util.Arrays;

/**
 * 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）
 * 使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * 链接：https://leetcode-cn.com/problems/perfect-squares/
 *
 * 这道题目的 BFS 解法见：
 *      https://github.com/jeffytang/leetcode/blob/master/7_queue/src/main/java/com/twq/queue/_279_PerfectSquares.java
 */
public class _279_PerfectSquares {
    // 暴力回溯法 (实际上就是递归)
    public int numSquares1(int n) {
        if (n == 0)
            return 0;

        int count = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            count = Math.min(count, numSquares1(n - i * i) + 1);
        }
        return count;
    }

    // 暴力回溯法 + 记忆化搜索
    public int numSquares2(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return numSquaresHelp(n, memo);
    }

    private int numSquaresHelp(int n, int[] memo) {
        if (memo[n] != -1)
            return memo[n];
        if (n == 0)
            return 0;

        int count = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            count = Math.min(count, numSquaresHelp(n - i * i, memo) + 1);
        }

        memo[n] = count;
        return count;
    }

    // 动态规划
    public int numSquares3(int n) {
        // 状态定义：dp[i] 表示和等于 i 的最小的平方数个数
        int[] dp = new int[n + 1];
        // 状态初始化
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        // 状态返回
        return dp[n];
    }
}
