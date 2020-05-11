package com.twq.dp;

import java.util.Arrays;

/**
 * 打家劫舍
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 *
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 链接：https://leetcode-cn.com/problems/house-robber
 */
public class _198_HouseRobber {
    // 普通递归
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        return tryRob1(nums, 0);
    }

    private int tryRob1(int[] nums, int start) {
        if (start >= nums.length)
            return 0;

        int res = 0;
        for (int i = start; i < nums.length; i++) {
            res = Math.max(res, nums[i] + tryRob1(nums, i + 2));
        }

        return res;
    }

    // 普通递归 + 记忆化搜索
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return tryRob2(nums, 0, memo);
    }

    private int tryRob2(int[] nums, int start, int[] memo) {
        if (start >= nums.length)
            return 0;

        if (memo[start] != -1) {
            return memo[start];
        }

        int res = 0;
        for (int i = start; i < nums.length; i++) {
            res = Math.max(res, nums[i] + tryRob2(nums, i + 2, memo));
        }

        memo[start] = res;

        return res;
    }

    // 动态规划 （从后往前扫描）
    public int rob3(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;

        // 状态定义：dp[i] 表示考虑抢劫 nums[i...n - 1] 所能获得的最大收益
        int[] dp = new int[len];

        // 状态初始化
        Arrays.fill(dp, -1);
        dp[len - 1] = nums[len - 1];

        // 状态转移
        for (int i = len - 2; i >= 0; i--) {
            // 求 dp[i]
            for (int j = i; j < len; j++) {
                dp[i] = Math.max(dp[i], nums[j] + (j + 2 < len ? dp[j + 2] : 0));
            }
        }

        // 返回结果
        return dp[0];

    }

    // 动态规划 （从前往后扫描）
    public int rob4(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;

        // 状态定义：dp[i] 表示考虑抢劫 nums[0...i) 所能获得的最大收益
        int[] dp = new int[len + 1];

        // 状态初始化
        dp[0] = 0;
        dp[1] = nums[0];

        // 状态转移
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }

        return dp[len];
    }

    // 动态规划 （从前往后扫描）
    // 因为 dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
    // 所以 dp[i] 只依赖 dp[i - 2] 和 dp[i - 1]，我们可以不用申请一个数组
    // 而是定义两个变量代替 dp[i - 2] 和 dp[i - 1]
    public int rob5(int[] nums) {
        int preMax = 0; // 代替 dp[i - 2]
        int curMax = 0; // 代替 dp[i - 1]
        for (int x : nums) {
            int tmp = curMax;
            curMax = Math.max(preMax + x, curMax);
            preMax = tmp;
        }
        return curMax;
    }
}
