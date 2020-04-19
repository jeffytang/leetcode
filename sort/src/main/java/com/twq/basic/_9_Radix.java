package com.twq.basic;

/**
 *  基数排序，基于计数排序实现
 *
 *  计数排序适用于范围比较小的输入，但是如果输入的范围比较大的话，可以使用基数排序
 *
 */
public class _9_Radix {

    public void sort(int[] nums) {
        // 1. 找到最大值
        int max = nums[0];
        for (int num : nums)
            max = Math.max(num, max);

        // 2. 对数组按照每个元素的每位进行计数排序
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(nums, exp);
    }

    /**
     * 对 nums 按照每个元素的指定位进行计数排序
     * @param nums  需要排序的数组
     * @param exp   指定的位
     */
    private void countSort(int[] nums, int exp) {
        int len = nums.length;
        int[] output = new int[len];
        // 之所以是 10，是因为数字只有 0...9 十个数字
        int[] count = new int[10];

        for (int i = 0; i < len; i++){
            // 个位数： (234 / 1) % 10 = 4
            // 十位数： (234 / 10) % 10 = 3
            // 百位数： (234 / 100) % 10 = 2
            int digit = (nums[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = len - 1; i >= 0; i--) {
            int digit = (nums[i] / exp) % 10;
            output[count[digit] - 1] = nums[i];
            count[digit]--;
        }

        for (int i = 0; i < len; i++)
            nums[i] = output[i];
    }
}
