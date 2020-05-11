package com.twq.dp.stock;

/**
 * 最佳买卖股票时机含冷冻期
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 */
public class _309_BestTimeToBuyAndSellStockWithCooldown {

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // 状态定义
        // dp[i][j] 表示 [0,i] 区间内，到第 i 天 (从 0 开始) 状态为 j 时的最大收益
        // 这里的 j 取三个值：
        //  0 表示不持股；1 表示持股；2 表示处在冷冻期
        int[][] dp = new int[len][3];

        // 状态初始化
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        // 状态转移
        for (int i = 1; i < len; i++) {
            // 不持股可以由这两个状态转换而来：
            //      （1）昨天不持股，今天什么都不操作，仍然不持股。
            //      （2）昨天持股，今天卖了一股。
            // 然后取最大值
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            // 持股可以由这两个状态转换而来：
            //      （1）昨天持股，今天什么都不操作，仍然持股；
            //      （2）昨天处在冷冻期，今天买了一股；
            // 然后取最大值
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] - prices[i]);

            // 处在冷冻期只可以由不持股转换而来，因为题目中说，刚刚把股票卖了，需要冷冻 1 天。
            dp[i][2] = dp[i - 1][0];
        }

        // 每一天都由前面几天的状态转换而来，最优值在最后一天。取不持股和冷冻期的最大者。
        return Math.max(dp[len - 1][0], dp[len - 1][2]);
    }

    // 空间压缩
    // 由于状态值就 3 个，还可以使用滚动变量的方式把状态压缩到一行。
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // 状态定义
        // dp[i] 表示状态为 i 时的最大收益
        // 这里的 i 取三个值：
        //  0 表示不持股；1 表示持股；2 表示处在冷冻期
        int[] dp = new int[3];

        // 状态初始化
        dp[0] = 0;
        dp[1] = -prices[0];
        dp[2] = 0;

        // 状态转移
        int preCash = dp[0];
        int preStock = dp[1];
        for (int i = 1; i < len; i++) {
            // 不持股可以由这两个状态转换而来：
            //      （1）昨天不持股，今天什么都不操作，仍然不持股。
            //      （2）昨天持股，今天卖了一股。
            // 然后取最大值
            dp[0] = Math.max(preCash, preStock + prices[i]);

            // 持股可以由这两个状态转换而来：
            //      （1）昨天持股，今天什么都不操作，仍然持股；
            //      （2）昨天处在冷冻期，今天买了一股；
            // 然后取最大值
            dp[1] = Math.max(preStock, dp[2] - prices[i]);

            // 处在冷冻期只可以由不持股转换而来，因为题目中说，刚刚把股票卖了，需要冷冻 1 天。
            dp[2] = preCash;

            preCash = dp[0];
            preStock = dp[1];
        }

        // 每一天都由前面几天的状态转换而来，最优值在最后一天。取不持股和冷冻期的最大者。
        return Math.max(dp[0], dp[2]);
    }
}
