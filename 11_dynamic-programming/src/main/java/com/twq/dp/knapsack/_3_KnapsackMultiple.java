package com.twq.dp.knapsack;

import java.util.Arrays;

/**
 * 多重背包问题
 *
 * 有NNN种物品和一个容量为VVV的背包。第 i 种物品最多有 p[i] 件可用，每件费用是 w[i]，价值是v[i]。
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 *  TODO
 */
public class _3_KnapsackMultiple {

    // 扩展 01 背包问题
    // f[i][j]=max(f[i−1][j−k∗w[i]]+k∗v[i])∣0<=k<=p[i]
    public int knapsack_1(int[] w, int[] v, int[] p, int C) {
        int len = w.length;
        if (len == 0) {
            return 0;
        }
        // 1. 状态定义：dp[i][j] 表示将 i 个物品放入容量为 j 的背包中的最大价值
        int[][] dp = new int[len][C + 1];

        // 2. 状态初始化
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int j = 0; j < len; j++) {
            dp[j][0] = 0;
        }
        for (int j = 0; j <= C; j++) {
            dp[0][j] = 0;
        }

        // 3. 状态转移
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= C; j++) {
                for (int k = 0; k <= p[i]; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * w[i]] + k * v[i]);
                }
            }
        }

        return dp[len - 1][C];
    }

    // 多重背包二进制拆分
    public int knapsack_2(int[] w, int[] v, int[] p, int C) {
        int[] dp = new int[C + 1];

        for (int i = 1; i <= w.length; i++) {
            int num = Math.min(p[i], C / w[i]);
            for (int k = 1; num > 0; k <<= 1) {
                if (k > num) k = num;
                num -= k;
                for (int j = C; j >= w[i] * k; j--) {
                    dp[j] = Math.max(dp[j], dp[j - w[i] * k] + v[i] * k);
                }
            }
        }

        return dp[C];
    }


}
