package com.twq.bit;

/**
 * 只出现一次的数字 III
 *
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 示例 :
 *
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 *
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 * 链接：https://leetcode-cn.com/problems/single-number-iii
 */
public class _260_SingleNumber_iii {
    /**
     *  参考： https://leetcode-cn.com/problems/single-number-iii/solution/zhi-chu-xian-yi-ci-de-shu-zi-iii-by-leetcode/
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;

        int diff = bitmask & (-bitmask); // 拿到 bitmask 对应二进制的最后一个 1

        int x = 0;
        for (int num : nums) {
            if ((num & diff) != 0) { // 找到和 diff 同一个位置上都是 1 的元素
                x ^= num;
            }
        }

        return new int[]{x, bitmask^x};
    }
}
