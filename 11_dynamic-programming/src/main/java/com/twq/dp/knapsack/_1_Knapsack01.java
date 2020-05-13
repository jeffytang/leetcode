package com.twq.dp.knapsack;

import java.util.Arrays;

/**
 *  0-1 背包问题
 *
 *  有一个背包，它的容量为 C，
 *  现有 n 种不同的物品，编号为 0...n-1，
 *  其中每一件物品的重量为 w(i)，价值为 v(i)
 *  问可以向这个背包中盛放哪些物品，
 *  使得在不超过背包容量的基础上，物品的总价值最大
 *
 *  -----------------------------------------------------------------------------------
 *
 *  这是最基础的背包问题，特点是：每种物品仅有一件，可以选择放或不放
 *  子问题的状态定义：f[i][j] 表示前 i 件物品恰放入一个容量为 j 的背包可以获得的最大价值
 *  状态转移方程是：f[i][j] = max(f[i - 1][j], f[i - 1][j + v[i]] + v[i])
 *
 *  这个方程非常重要，基本所有和背包相关的问题的方程都是由它衍生出来的。所以有必要将它详细解释下：
 *  "将前 i 件物品放入容量为 j 的背包中" 这个子问题，若只考虑第 i 件物品的粗略 (放或不放)，
 *  那么就可以转化为一个只牵扯前 i - 1件物品的问题。
 *      1. 如果不放第 i 件物品，那么问题就转化为 "前 i - 1 件物品放入容量为 j 的背包中"，价值为 f[i - 1][j]
 *      2. 如果放第 i 件物品，那么问题就转化为 "前 i - 1 件物品放入剩下的容量为 j - w[i] 的背包中"，
 *      此时能获得的最大价值就是 f[i - 1][j - w[i]] 再加上通过放入第 i 件物品获得的价值 v[i]
 *  TODO
 */
public class _1_Knapsack01 {
    // 普通递归
    public int knapsack01_1(int[] w, int[] v, int C) {
        return bestValue(w, v, v.length - 1, C);
    }

    // 递归公式：
    // F(i, c) 表示考虑将 i 个物品放进容量为 c 的背包，使得价值最大
    // F(i, c) = max(F(i - 1, c), v(i) + F(i - 1, c - w(i)))
    // 其中：
    //      F(i - 1, c) 表示不考虑将第 i 个物品放进去的最大价值
    //      v(i) + F(i - 1, c - w(i)) 表示考虑将第 i 个物品放进去的最大价值
    private int bestValue(int[] w, int[] v, int index, int c) {
        if (index < 0 || c < 0) {
            return 0;
        }

        int res = bestValue(w, v, index - 1, c);
        if (c >= w[index]) {
            res = Math.max(res, v[index] + bestValue(w, v, index - 1, c - w[index]));
        }

        return res;
    }

    // 普通递归 + 记忆化搜索
    private int[][] memo;
    public int knapsack01_2(int[] w, int[] v, int C) {
        int len = w.length;
        memo = new int[len][C + 1];
        for (int i = 0; i < len; i++) {
            Arrays.fill(memo[i], -1);
        }
        return bestValue2(w, v, len - 1, C);
    }

    // 递归公式：
    // F(i, c) 表示考虑将 i 个物品放进容量为 c 的背包，使得价值最大
    // F(i, c) = max(F(i - 1, c), v(i) + F(i - 1, c - w(i)))
    // 其中：
    //      F(i - 1, c) 表示不考虑将第 i 个物品放进去的最大价值
    //      v(i) + F(i - 1, c - w(i)) 表示考虑将第 i 个物品放进去的最大价值
    private int bestValue2(int[] w, int[] v, int index, int c) {
        if (index < 0 || c < 0) {
            return 0;
        }

        if (memo[index][c] != -1) {
            return memo[index][c];
        }

        int res = bestValue2(w, v, index - 1, c);
        if (c >= w[index]) {
            res = Math.max(res, v[index] + bestValue2(w, v, index - 1, c - w[index]));
        }

        memo[index][c] = res;

        return res;
    }

    // 动态规划
    public int knapsack01_3(int[] w, int[] v, int C) {
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
        // 考虑将第 0 号物品放入背包中
        for (int j = 0; j <= C; j++) {
            dp[0][j] = (j >= w[0] ? w[0] : 0);
        }

        // 3. 状态转移
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= C; j++) {
                // 不考虑将第 i 个物品放进去的最大价值
                dp[i][j] = dp[i - 1][j];
                if (j > w[i]) {
                    // 考虑将第 i 个物品放进去的最大价值
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i - 1][j - w[i]]);
                }
            }
        }

        // 4. 返回结果
        return dp[len - 1][C];
    }

    // 动态规划 + 空间压缩
    public int knapsack01_4(int[] w, int[] v, int C) {
        int len = w.length;
        if (len == 0) {
            return 0;
        }

        int[] dp = new int[C + 1];

        Arrays.fill(dp, -1);
        // 考虑将第 0 号物品放入背包中
        for (int j = 0; j <= C; j++) {
            dp[j] = (j >= w[0] ? w[0] : 0);
        }

        // 状态转移
        for (int i = 0; i < len; i++) {
            for (int j = C; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }

        // 返回结果
        return dp[C];
    }
}
