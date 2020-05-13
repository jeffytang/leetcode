package com.twq.dp.knapsack;

/**
 *  分组背包问题
 *
 *  有 N 件物品和一个容量为 V 的背包。第 i 件物品的费用是 w[i]，价值是 v[i]。
 *  这些物品被划分为若干组，每组中的物品互相冲突，最多选一件。
 *  求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 *
 *  TODO
 */
public class _5_GroupCost {
    /*
        这个问题变成了每组物品有若干种策略：是选择本组的某一件，还是一件都不选。
        也就是说设 f[k][j] 表示前 k 组物品花费费用 j 能取得的最大权值，则有：

        f[k][j]=max(f[k−1][j],f[k−1][j−c[i]]+w[i]∣物品i属于组k)

        int[][] a 表示某个物品属于某个分组
        a[i][k] 表示物品 i 属于第 k 组，它的价值是 a[i][k]
     */
    public int knapsack_1(int[][] a, int V) {
        int n = a.length;
        int m = a[0].length;
        int[] dp = new int[V + 1];
        for (int i = 1; i <= n; i++)
            for (int j = m; j >= 0; j--)
                for (int k = 1; k <= j; k++)
                    dp[j] = Math.max(dp[j], dp[j - k] + a[i][k]);
        return dp[m];
    }
}
