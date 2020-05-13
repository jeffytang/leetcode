package com.twq.dp.knapsack;

import java.util.Arrays;

/**
 * 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 链接：https://leetcode-cn.com/problems/coin-change
 */
public class _322_CoinChange {

    public int coinChange(int[] coins, int amount) {
        // 1. 状态定义：dp[i] 表示凑齐总价值 i 需要最少硬币数
        int[] dp = new int[amount + 1];

        // 2. 状态初始化
        // 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
        Arrays.fill(dp, amount + 1);

        // 3. 状态转移
        // 注意：要求的是恰好填满「背包」，所以初始化的时候需要赋值为一个不可能的值：amount + 1。
        // 只有在有「正常值」的时候，「状态转移」才可以正常发生。
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != amount + 1) {
                    // 1 + dp[i - coin] 表示增加一个硬币
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        // 4. 返回结果
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    // 套用完全背包的逻辑
    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == amount + 1 ? 0 : dp[amount];
    }


}
