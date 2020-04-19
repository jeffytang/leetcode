package com.twq.tp;

/**
 *  给你一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 *  元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 *  给定 nums = [3,2,2,3], val = 3,
 *  函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *  你不需要考虑数组中超出新长度后面的元素。
 *
 *  给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 *  函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 *  注意这五个元素可为任意顺序。
 *
 */
public class _27_RemoveElement {

    // 暴力解法
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int removeElement1(int[] nums, int val) {
        int[] tmp = new int[nums.length];
        int i = 0;
        for (int num : nums)
            if (num != val)
                tmp[i++] = num;

        for (int j = 0; j < i; j++)
            nums[j] = tmp[j];

        return i;
    }

    // 双指针
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int removeElement2(int[] nums, int val) {
        int slow = 0, fast = 0;
        for (; fast < nums.length; fast++)
            if (nums[fast] != val)
                nums[slow++] = nums[fast];
        return slow;
    }

}
