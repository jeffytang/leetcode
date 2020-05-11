package com.twq.dp.stock;

/**
 * 买卖股票的最佳时机
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），
 * 设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 */
public class _121_BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }

        // 状态定义
        // dp[i][j] 表示第 i 天处于状态 j 获取到的最大利益
        // 其中 j 可以取值为： 0 表示不持股；1 表示持股
        int[][] dp = new int[len][2];

        // 状态初始化
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 状态转移
        for (int i = 1; i < len; i++) {
            // 今天我没有持有股票，有两种可能：
            //      1. 我昨天就没有持有，然后今天选择继续没有持有，所以我今天还是没有持有；
            //      2. 我昨天持有股票，但是今天我选择卖了股票了，所以我今天没有持有股票了。
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            // 今天我持有着股票，有两种可能：
            //      1. 我昨天就持有着股票，然后今天选择继续持有，所以我今天还持有着股票；
            //      2. 我昨天本没有持有，但今天我选择买了股票，所以今天我就持有股票了。
            // 关键点：因为只能买卖一次，所以这里是 -prices[i]，而不是 dp[i - 1][0] - prices[i]
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[len - 1][0];
    }

    // 注意一下状态转移方程，新状态只和相邻的一个状态有关，其实不用整个 dp 数组，
    // 只需要一个变量储存相邻的那个状态就足够了，这样可以把空间复杂度降到 O(1):
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        // 状态转移
        for (int i = 0; i < len; i++) {

            //dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);

            //dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }

        return dp_i_0;
    }


    public int maxProfit2(int[] prices) {
        // 存储前 i - 1 天最低的价格
        int minPrice = Integer.MAX_VALUE;
        // 存储最大的收益
        int maxP = 0;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            // 要么卖出，那么收益是 prices[i] - minPrice
            // 要么不卖，收益是前一天计算出来的 maxP
            // 然后取最大值
            maxP = Math.max(maxP, prices[i] - minPrice);
        }
        return maxP;
    }
}
