package com.twq.bs;

/**
 * 输入: [3,4,5,1,2]
 * 输出: 1
 *
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 */
public class _153_FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1; // 这里必须减 1，因为后面需要访问 nums[r]
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 只要 mid 所在的元素大于 r 所在的元素，就往右搜索
            if (nums[mid] > nums[r])
                l = mid + 1;
            else
                r = mid;
        }
        return nums[l];
    }
}
