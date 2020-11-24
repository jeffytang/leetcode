package com.twq.sort;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 */
public class _75_SortColors {

    // 计数排序 (两趟扫描)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public void sortColors1(int[] nums) {
        // 1. 计数 add user
        int[] count = new int[3];
        for (int num : nums)
            count[num]++;

        //
        int k = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < count[i]; j++)
                nums[k++] = i;
    }

    // 三路快排 (一趟扫描)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    // 参考：https://leetcode-cn.com/problems/sort-colors/solution/kuai-su-pai-xu-partition-guo-cheng-she-ji-xun-huan/
    public void sortColors2(int[] nums) {
        /*
         * Partitioning degenerates to the traditional 3-way
         * (or "Dutch National Flag") schema:
         *
         *   left part    center part              right part
         * +-------------------------------------------------+
         * |   == 0     |   == 1      |     ?   |   == 2     |
         * +-------------------------------------------------+
         *              ^              ^        ^
         *              |              |        |
         *             zero            i      two
         *
         * Invariants:
         *
         *   all in (left, zero)   < pivot
         *   all in [zero, i)     == pivot
         *   all in (two, right) > pivot
         *
         * Pointer i is the first index of ?-part.
         */
        int zero = -1; // nums[0...zero] == 0
        int two = nums.length; // nums[two...nums.length - 1] == 2
        for (int i = 0; i < nums.length; ) {
            if (nums[i] == 1)
                i++;
            else if (nums[i] == 2) {
                two--;
                swap(nums, i, two);
            } else { // nums[i] == 0
                zero++;
                swap(nums, zero, i);
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
