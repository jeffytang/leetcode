package com.twq.bs;

/**
 *  输入: nums = [4,5,6,7,0,1,2], target = 0
 *  输出: 4
 *
 *  输入: nums = [4,5,6,7,0,1,2], target = 3
 *  输出: -1
 */
public class _33_SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[l] <= nums[mid]) { // mid 在左边有序的数组中
                // target 在左边有序数组中 mid 的左边
                if (nums[mid] > target && nums[l] <= target)
                    r = mid - 1;
                else
                    l = mid + 1;
            } else { // mid 在右边有序的数组中
                // target 在右边有序数组中 mid 的右边
                if (nums[mid] < target && nums[r] >= target)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }
        return -1;
    }
}
