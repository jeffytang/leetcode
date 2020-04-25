package com.twq.ll.slidingwindow;

/**
 *  给定一个含有 n 个正整数的数组和一个正整数 s ，
 *  找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
 *  如果不存在符合条件的连续子数组，返回 0。
 *
 *  输入: s = 7, nums = [2,3,1,2,4,3]
 *  输出: 2
 *  解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 */
public class _209_MinimumSizeSubarraySum {

    public int minSubArrayLen(int s, int[] nums) {
        int ans = nums.length + 1;

        int left = 0, right = -1;
        int windowSum = 0;

        while (left < nums.length) {
            // 维护右指针
            if (right + 1 < nums.length && windowSum < s) {
                right++;
                windowSum += nums[right];
            } else { // 维护左指针
                windowSum -= nums[left];
                left++;
            }

            // 更新结果
            if (windowSum >= s)
                ans = Math.min(ans, right - left + 1);
        }

        return ans == nums.length + 1 ? 0 : ans;
    }
}
