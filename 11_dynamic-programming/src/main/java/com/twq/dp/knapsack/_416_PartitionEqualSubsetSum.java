package com.twq.dp.knapsack;

import java.util.Arrays;

/**
 * 分割等和子集
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 *
 * 示例 2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 */
public class _416_PartitionEqualSubsetSum {
    /*
        这个问题可以抽象成 0-1 背包问题:
            背包的容量是 nums 数组元素和的一半 (假设为 sum/2)
            所以，问题抽象为将数组中的元素放入到容量为 sum/2 的背包中，看看是否可以填满

        该问题的状态转移方程为：
            F(i, C) = F(i - 1, C) || F(i - 1, C - w[i])
        其中：
            F(i, C) 表示 i 个元素是否可以填满容量 C 的背包
            F(i - 1, C) 表示 i - 1个元素是否可以填满容量 C 的背包，即不把第 i 个元素放入背包
            F(i - 1, C - w[i]) 表示 i - 1个元素是否可以填满容量 C - w[i] 的背包，即将第 i 个元素放入背包
     */

    // 递归 + 记忆化搜索
    // memo[i][j] 表示使用索引为 [0...i] 的这些元素，是否可以完全填充一个容量为 j 的背包
    // -1 表示未计算，0 表示不可以填充，1 表示可以填充
    private int[][] memo;
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }
        memo = new int[nums.length][sum / 2 + 1];
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return tryPartition(nums, nums.length - 1, sum / 2);
    }

    private boolean tryPartition(int[] nums, int index, int sum) {
        if (sum == 0) {
            return true;
        }

        if (index < 0 || sum < 0) {
            return false;
        }

        if (memo[index][sum] != -1) {
            return memo[index][sum] == 1;
        }

        boolean canp = tryPartition(nums, index - 1, sum)
                || tryPartition(nums, index - 1, sum - nums[index]);

        memo[index][sum] = canp ? 1 : 0;

        return canp;
    }

    // 动态规划
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }
        int len = nums.length;
        int c = sum / 2;
        // 状态定义：dp[i][j] 表示前 i 个元素是否可以填充容量为 j 的背包
        boolean[][] dp = new boolean[len][c + 1];

        // 状态初始化：考虑第一个元素
        for (int i = 0; i <= c; i++) {
            dp[0][i] = (nums[0] == i);
        }

        // 状态转移
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= c; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        // 返回结果
        return dp[len - 1][c];
    }

    // 动态规划 + 空间压缩
    public boolean canPartition3(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }
        int len = nums.length;
        int c = sum / 2;

        boolean[] dp = new boolean[c + 1];

        for (int i = 0; i <= c; i++) {
            dp[i] = (nums[0] == i);
        }

        // 状态转移
        for (int i = 1; i < len; i++) {
            for (int j = c; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];

            }
        }

        // 返回结果
        return dp[c];
    }
}
