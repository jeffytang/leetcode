package com.twq.st;


import java.util.*;

/**
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，
 * 使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 *
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 *
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 *
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 */
public class _220_ContainsDuplicate_iii {
    // 平衡二叉搜索树
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 找到大于 nums[i] 的最小值
            Integer c = set.ceiling(nums[i]);
            if (c != null && c <= nums[i] + t) return true;

            // 找到小于 nums[i] 的最大值
            Integer f = set.floor(nums[i]);
            if (f != null && nums[i] <= f + t) return true;

            set.add(nums[i]);
            // 维护窗口
            if (set.size() == k + 1) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        _220_ContainsDuplicate_iii e = new _220_ContainsDuplicate_iii();
        System.out.println(e.containsNearbyAlmostDuplicate1(new int[]{1,2,3,1}, 3, 0));
    }
}
