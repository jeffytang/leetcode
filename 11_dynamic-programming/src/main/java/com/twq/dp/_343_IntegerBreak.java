package com.twq.dp;

/**
 * 整数拆分
 *
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * 链接：https://leetcode-cn.com/problems/integer-break
 */
public class _343_IntegerBreak {
    // 普通递归解法
    public int integerBreak1(int n) {
        if (n == 1)
            return 1;
        int res = -1;
        for (int i = 1; i <= n - 1; i++) {
            // 将 n 分割成 i 、 n - i
            // 那么 i * (n - i) 表示的是将 n 分割成两部分的乘积
            // i * integerBreak1(n - i) 表示将 n 分割成两部分以上的乘积
            res = Math.max(res, Math.max(i * (n - i), i * integerBreak1(n - i)));
        }
        return res;
    }

    // 递归 + 记忆化搜索 （自顶而下）
    public int integerBreak2(int n) {
        int[] memo = new int[n + 1];
        return breakInteger(n, memo);
    }

    private int breakInteger(int n, int[] memo) {
        if (memo[n] != 0)
            return memo[n];
        if (n == 1)
            return 1;
        int res = -1;
        for (int i = 1; i <= n - 1; i++) {
            res = Math.max(res, Math.max(i * (n - i), i * breakInteger(n - i, memo)));
        }
        memo[n] = res;
        return res;
    }

    // 动态规划
    public int integerBreak3(int n) {
        // 状态定义：dp[i] 表示数字 i 分割后(至少分割成两部分)得到的最大乘积
        int[] dp = new int[n + 1];

        // 状态初始化
        dp[1] = 1;

        // 状态转移
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                // 将 i 分割成 j 、 i - j 两部分
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        // 返回结果
        return dp[n];
    }
}
