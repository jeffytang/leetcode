package com.twq.dp;

import java.util.Arrays;

/**
 * 斐波那契数
 *
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 给定 N，计算 F(N)。
 *
 * 示例 1：
 *
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
 * 示例 2：
 *
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
 * 示例 3：
 *
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
 *  
 *
 * 提示：
 *
 * 0 ≤ N ≤ 30
 *
 * 链接：https://leetcode-cn.com/problems/fibonacci-number
 */
public class _509_FibonacciNumber {
    // 普通递归解法
    public int fib1(int N) {
        if (N == 0)
            return 0;
        if (N == 1)
            return 1;

        return fib1(N - 1) + fib1(N - 2);
    }

    // 记忆化搜索，自上而下的解决问题
    public int fib2(int N) {
        int[] memo = new int[N + 1];
        return f(N, memo);
    }

    private int f(int n, int[] memo) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        if (memo[n] != 0)
            return memo[n];

        int res = f(n - 1, memo) + f(n - 2, memo);
        memo[n] = res;
        return res;
    }

    // 动态规划，自下而上解决问题
    public int fib3(int N) {
        if (N <= 0)
            return 0;
        // 1. 状态定义：dp[i] 表示数字 i 的斐波那契数字
        int[] dp = new int[N + 1];

        // 2. 状态初始化
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;

        // 3. 状态转移
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 4. 返回结果
        return dp[N];
    }
}
