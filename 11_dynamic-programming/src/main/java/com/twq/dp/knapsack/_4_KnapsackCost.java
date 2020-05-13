package com.twq.dp.knapsack;

/**
 *  二维费用背包问题
 *
 *  二维费用的背包问题是指：对于每件物品，具有两种不同的费用；
 *  选择这件物品必须同时付出这两种代价；对于每种代价都有一个可付出的最大值（背包容量）。
 *  问怎样选择物品可以得到最大的价值。
 *
 *  设这两种代价分别为代价1和代价2，第i件物品所需的两种代价分别为w[i]和g[i]。
 *  两种代价可付出的最大值（两种背包容量）分别为V和T。物品的价值为v[i]。
 *
 *  TODO
 */
public class _4_KnapsackCost {
    /*
        费用加了一维，只需状态也加一维即可。
        设 f[i][j][k] 表示前 i 件物品付出两种代价分别为 j 和 k 时可获得的最大价值。

        f[i][j][k]=max(f[i−1][j][k],f[i−1][j−w[i]][k−g[i]]+v[i])
     */
    public int knapsack_1(int[] w, int[] g, int T, int V, int[] v) {
        int[][] dp = new int[T + 1][V + 1];
        for (int i = 1; i <= w.length; i++) {
            for (int j = T; j >= w[i]; j--) {
                for (int k = V; k >= g[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - w[i]][k - g[i]] + v[i]);
                }
            }
        }
        return dp[T][V];
    }
}
