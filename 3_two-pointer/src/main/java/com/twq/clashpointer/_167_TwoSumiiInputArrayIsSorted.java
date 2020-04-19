package com.twq.clashpointer;

/**
 *  给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 *  函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 *  说明：
 *      1. 返回的下标值（index1 和 index2）不是从零开始的。
 *      2. 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 *  输入: numbers = [2, 7, 11, 15], target = 9
 *  输出: [1,2]
 *  解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class _167_TwoSumiiInputArrayIsSorted {

    // 时间复杂度 O(n)
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if ( sum == target)
                return new int[]{left + 1, right + 1};
            else if (sum < target)
                left++;
            else
                right++;
        }

        return new int[]{-1, -1};
    }
}
