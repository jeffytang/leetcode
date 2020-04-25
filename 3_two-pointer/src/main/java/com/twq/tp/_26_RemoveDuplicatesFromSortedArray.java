package com.twq.ll.tp;

/**
 *  给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度
 *
 *  给定数组 nums = [1,1,2],
 *  函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *  你不需要考虑数组中超出新长度后面的元素。
 *
 *  给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *  函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 */
public class _26_RemoveDuplicatesFromSortedArray {
    // 双指针
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int removeDuplicates(int[] nums) {
        int slow = 0, fast = 0;
        for (; fast < nums.length; fast++)
            if (nums[fast] != nums[slow])
                // slow 先自加 1，是为了保证元素的顺序
                nums[++slow] = nums[fast];
        return slow + 1;
    }


}
