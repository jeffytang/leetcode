package com.twq.tp;

/**
 *  给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 *  输入: [0,1,0,3,12]
 *  输出: [1,3,12,0,0]
 */
public class _283_MoveZeroes {

    // 暴力解法
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public void moveZeroes1(int[] nums) {
        int[] tmp = new int[nums.length];
        int i = 0;
        for (int num : nums) {
            if (num != 0) {
                tmp[i] = num;
                i++;
            }
        }
        for (int j = 0; j < nums.length; j++)
            nums[j] = tmp[j];
    }

    // 双指针解法 (直接覆盖)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public void moveZeroes2(int[] nums) {
        // slow : 表示 [0...slow) 的元素均为非 0 元素
        // fast : 用于遍历数组，保证 [0...fast] 中所有非 0 元素都按照顺序排列在 [0...slow) 中
        int slow = 0, fast = 0;

        for (; fast < nums.length; fast++)
            if (nums[fast] != 0)
                nums[slow++] = nums[fast];
        // 将剩余的元素设置为 0
        for (int i = slow; i < nums.length; i++)
            nums[i] = 0;
    }


    // 双指针解法 (交换)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public void moveZeroes3(int[] nums) {
        // slow : 表示 [0...slow) 的元素均为非 0 元素
        // fast : 用于遍历数组，保证 [0...fast] 中所有非 0 元素都按照顺序排列在 [0...slow) 中
        int slow = 0, fast = 0;

        for (; fast < nums.length; fast++)
            if (nums[fast] != 0){
                if (slow != fast) {
                    // 交换
                    int tmp = nums[slow];
                    nums[slow] = nums[fast];
                    nums[fast] = tmp;

                }
                slow++;
            }
    }


}
