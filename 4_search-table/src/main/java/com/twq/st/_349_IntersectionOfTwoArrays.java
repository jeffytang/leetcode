package com.twq.st;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *  给定两个数组，编写一个函数来计算它们的交集。
 *
 *  输入: nums1 = [1,2,2,1], nums2 = [2,2]
 *  输出: [2]
 *
 *  输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *  输出: [9,4]
 *
 *  说明：
 *      1. 输出结果中的每个元素一定是唯一的。
 *      2. 我们可以不考虑输出结果的顺序。
 */
public class _349_IntersectionOfTwoArrays {
    // 两个 set，set 判断存在
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    public int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> records = new HashSet<>();
        for (int num : nums1)
            records.add(num);

        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2) {
            if (records.contains(num))
                resultSet.add(num);
        }

        int[] ans = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet)
            ans[i++] = num;

        return ans;
    }

    // 一个 set，二分查找判断存在
    // 时间复杂度：O(nlogn)
    // 空间复杂度 O(n)
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);

        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2)
            if (binarySearch(nums1, num))
                resultSet.add(num);

        int[] ans = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet)
            ans[i++] = num;

        return ans;
    }

    private boolean binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }

    // 双指针
    public int[] intersection3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> resultSet = new HashSet<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                resultSet.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }

        int[] ans = new int[resultSet.size()];
        int k = 0;
        for (int num : resultSet)
            ans[k++] = num;

        return ans;
    }
}
