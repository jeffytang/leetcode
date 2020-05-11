package com.twq.dp.stock;

/**
 * 买卖股票的最佳时机 IV
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，
 * 这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例 2:
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出,
 * 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出,
 * 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 */
public class _188_BestTimeToBuyAndSellStock_iv {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k == 0 || len < 2) return 0;

        if ( k >= len / 2) {
            // 使用贪心算法求解
            return greedy(prices, len);
        }

        // 1. 状态定义
        // dp[i][j][K] : 表示到下标为 i 的天数为止(从 0 开始)，发生了 j 次交易次数(从 0 开始)
        // 状态为 K 的最大利润
        // K = 1 表示持股，K = 0 表示不持股
        int[][][] dp = new int[len][k][2];

        // 2. 状态初始化
        // 所有不持股的的状态值都初始化为 0
        // 所有持股的状态值都初始化为 Integer.MIN_VALUE，表示利润未知
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j][1] = Integer.MIN_VALUE;
            }
        }

        // 3. 状态转移
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < k; j++) {
                if (i == 0) {   // 在第一天
                    // 第一天持股的利润
                    dp[i][j][1] = -prices[i];
                    // 第一天不持股的利润
                    dp[i][j][0] = 0;
                } else {
                    if (j == 0) { // 第一次交易
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], -prices[i]);
                    } else {
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                    }
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                }
            }
        }

        return dp[len - 1][k - 1][0];
    }

    private int greedy(int[] prices, int len) {
        // 转换为股票系列的第 2 题，使用贪心算法完成，思路是只要有利润，就交易
        int res = 0;
        for (int i = 1; i < len; i++) {
            if (prices[i - 1] < prices[i]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}
