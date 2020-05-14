package com.twq.dp;

import java.util.Arrays;

/**
 * 最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 */
public class _300_LongestIncreasingSubsequence {
    // 动态规划
    // 时间复杂度：O(n^2)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = Integer.MIN_VALUE;

        // 状态定义：dp[i] 表示前 i 个元素的最长子序列的长度
        int[] dp = new int[nums.length];

        // 状态初始化，每一个元素肯定有自己作为最长子序列
        Arrays.fill(dp, 1);

        // 状态转移
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                    res = Math.max(res, dp[i]);
                }
            }
        }

        return res == Integer.MIN_VALUE ? 1 : res;
    }

    // 动态规划 + 二分查找
    // 时间复杂度：O(nlogn)
    // 参考： https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/
    public int lengthOfLIS_1(int[] nums) {
        // tails[k] 的值代表长度为 k+1 子序列的尾部元素值。
        int tails[] = new int[nums.length];

        // 转移方程： 设 res 为 tails 当前长度，代表直到当前的最长上升子序列长度。
        // 设 j∈[0,res)，考虑每轮遍历 nums[k] 时，通过二分法遍历 [0,res) 列表区间，
        // 找出 nums[k] 的大小分界点，会出现两种情况：
        //  1. 区间中存在 tails[i] > nums[k]： 将第一个满足 tails[i] > nums[k]
        //  执行 tails[i] = nums[k] ；因为更小的 nums[k] 后更可能接一个比它大的数字（前面分析过）。
        //  2. 区间中不存在 tails[i] > nums[k]： 意味着 nums[k] 可以接在前面所有长度的子序列之后，
        //  因此肯定是接到最长的后面（长度为 res ），新子序列长度为 res + 1。
        int res = 0;
        for (int num : nums) {
            int left = 0, right = res;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) left = mid + 1;
                else right = mid;
            }
            tails[left] = num;
            if (res == right) res++;
        }

        return res;
    }
}
