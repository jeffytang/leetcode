package com.twq.st;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 *
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 *
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 *
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class _219_ContainsDuplicate_ii {
    // 滑动窗口 + 查找表
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int left  = 0;
        for (int right = 0; right < nums.length; right++) {
            if (map.containsKey(nums[right]) && map.get(nums[right]) > 0)
                return true;
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            if (right - left == k) {
                map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);
                left++;
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        // key 是数组中的每个元素
        // value 是数组中元素对应的索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                if (i - map.get(nums[i]) <= k)
                    return true;
            map.put(nums[i], i);
        }
        return false;
    }

    // 使用 HashSet
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))
                return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
