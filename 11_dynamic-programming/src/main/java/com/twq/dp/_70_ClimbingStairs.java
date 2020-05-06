package com.twq.dp;

import java.util.Arrays;

/**
 * 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 */
public class _70_ClimbingStairs {
    // 普通递归 （超时）
    public int climbStairs1(int n) {
        if (n == 0 || n == 1)
            return 1;
        if (n == 2)
            return 2;

        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    // 递归 + 记忆化搜索
    public int climbStairs2(int n, int[] memo) {
        if (n == 0 || n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (memo[n] != 0)
            return memo[n];
        int res = climbStairs2(n - 1, memo) + climbStairs2(n - 2, memo);
        memo[n] = res;
        return res;
    }

    // 动态规划
    public int climbStairs3(int n) {
        if (n == 0)
            return 1;
        if (n <= 2)
            return n;

        // 1. 状态定义：dp[i] 表示爬到第 i 阶楼梯的爬法
        int[] dp = new int[n + 1];

        // 2. 状态初始化
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        // 3. 状态转移
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 4. 返回结果
        return dp[n];
    }
}
