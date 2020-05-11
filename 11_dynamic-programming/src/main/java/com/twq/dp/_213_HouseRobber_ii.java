package com.twq.dp;

/**
 * 打家劫舍 II
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 */
public class _213_HouseRobber_ii {
    // 本地思路其实就是在 _198_HouseRobber 的基础上做两次动态规划：
    // 1. 不抢最后一个房子能获取到的最大利益
    // 2， 不抢第一个房子能获取到的最大利益
    // 然后求最大值
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int notRobLastHouse = tryRob(nums, 0, nums.length - 2);
        int notRobFirstHouse = tryRob(nums, 1, nums.length - 1);

        return Math.max(notRobLastHouse, notRobFirstHouse);
    }

    // 动态规划 （从前往后扫描）
    // 因为 dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
    // 所以 dp[i] 只依赖 dp[i - 2] 和 dp[i - 1]，我们可以不用申请一个数组
    // 而是定义两个变量代替 dp[i - 2] 和 dp[i - 1]
    public int tryRob(int[] nums, int start, int end) {
        int preMax = 0; // 代替 dp[i - 2]
        int curMax = 0; // 代替 dp[i - 1]
        for (int i = start; i <= end; i++) {
            int tmp = curMax;
            curMax = Math.max(preMax + nums[i], curMax);
            preMax = tmp;
        }
        return curMax;
    }
}
