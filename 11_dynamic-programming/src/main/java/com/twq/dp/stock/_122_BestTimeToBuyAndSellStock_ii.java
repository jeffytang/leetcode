package com.twq.dp.stock;

/**
 * 买卖股票的最佳时机 II
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 *
 * 提示：
 *
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 *
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 */
public class _122_BestTimeToBuyAndSellStock_ii {
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
            // 关键点：因为可以无限次买卖，所以这里是 dp[i - 1][0] - prices[i]，而不是 -prices[i]
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
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
            dp_i_1 = Math.max(dp_i_1, dp_i_0 - prices[i]);
        }

        return dp_i_0;
    }

    // 贪心算法
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i] - prices[i - 1];
            // 只有上涨的时候才买卖
            if (tmp > 0) maxProfit += tmp;
        }
        return maxProfit;
    }
}
