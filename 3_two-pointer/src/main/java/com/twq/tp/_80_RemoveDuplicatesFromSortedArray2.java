package com.twq.ll.tp;

/**
 *  给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 *  给定数组 nums = [1,1,1,2,2,3],
 *  函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *  你不需要考虑数组中超出新长度后面的元素。
 *
 *  给定 nums = [0,0,1,1,1,1,2,3,3],
 *  函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 */
public class _80_RemoveDuplicatesFromSortedArray2 {
    // 双指针
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <= 2) return nums.length;

        int slow = 1, fast = 2;
        for (; fast < nums.length; fast++)
            // 保证至少有两个元素相等
            if (nums[fast] != nums[slow - 1])
                // slow 先自加 1，是为了保证元素的顺序
                nums[++slow] = nums[fast];
        return slow + 1;
    }


}
