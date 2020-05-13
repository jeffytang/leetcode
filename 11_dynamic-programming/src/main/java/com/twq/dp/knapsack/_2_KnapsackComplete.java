package com.twq.dp.knapsack;

import java.util.Arrays;

/**
 * 完全背包问题
 *
 *  有一个背包，它的容量为 C，
 *  现有 n 种不同的物品，编号为 0...n-1，
 *  每种物品都有无限件可用
 *  其中每一件物品的重量为 w(i)，价值为 v(i)
 *  问可以向这个背包中盛放哪些物品，
 *  使得在不超过背包容量的基础上，物品的总价值最大
 *  TODO
 */
public class _2_KnapsackComplete {

    /*
     *  方法一：扩展 0-1 背包问题
     *  基本思路：
     *  这个问题非常类似于 01 背包问题，所不同的是每种物品有无限件，也就是从每种物品的角度考虑，
     *  与它相关的策略已并非取或不取两种，而是有取 0 件、取 1 件、取 2 件 ... 等很多种
     *  如果仍然按照解 01 背包时的思路，令 f[i][j] 表示前 i 种物品恰放入一个容量为 C 的背包的最大权值。
     *  仍然可以按照每种物品不同的策略写出状态转移方程：
     *      f[i][j] = max(f[i - 1][j - k * w[i]] + k * v[i]) | 0 <= k *w[i] <= j
     *  这跟 01背包问题一样有 O(VN) 个状态需要求解，但求解每个状态的时间已经不是常数了，
     *  求解状态 f[i][j] 的时间是 O(V/w[i])，总的复杂度可以认为是O(N∗Σ(V/w[i]))，是比较大的。
     *  将 01背包问题的基本思路加以改进，得到了这样一个清晰的方法。
     *  这说明 01背包问题的方程的确是很重要，可以推及其它类型的背包问题。但我们还是试图改进这个复杂度。
     */
    public int knapsack_1(int[] w, int[] v, int C) {
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
                int count = j / w[i];
                for (int k = 0; k <= count; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * w[i]] + k * v[i]);
                }
            }
        }

        return dp[len - 1][C];
    }


    public int knapsack_2(int[] w, int[] v, int C) {
        int len = w.length;
        if (len == 0) {
            return 0;
        }

        int[] dp = new int[C + 1];

        Arrays.fill(dp, -1);

        // 状态转移
        for (int i = 0; i < len; i++) {
            for (int j = w[i]; j <= C; j++) {
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }

        // 返回结果
        return dp[C];
    }
}
