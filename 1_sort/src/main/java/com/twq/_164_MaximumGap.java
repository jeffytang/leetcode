package com.twq;

import java.util.Arrays;

/**
 *  给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *
 *  如果数组元素个数小于 2，则返回 0。
 *
 *  输入: [3,6,9,1]
 *  输出: 3
 *  解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 *
 *  输入: [10]
 *  输出: 0
 *  解释: 数组元素个数小于 2，因此返回 0。
 *
 *  你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 *  请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */
public class _164_MaximumGap {
    // 快速排序
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(1)
    public int maximumGap1(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        Arrays.sort(nums);

        int maxGap = 0;
        for (int i = 0; i < nums.length - 1; i++)
            maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);

        return maxGap;
    }

    // 基数排序
    // 时间复杂度：
    //      基数排序的时间复杂度是 O(d*(n + k))
    //      但是在这里 d <= 10(在我们的例子中，最大可能的 32 位有符号是 2147483647)，
    //      k = 10，所以时间复杂度是 O(n)
    // 空间复杂度是 O(n);
    public int maximumGap2(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        // 1. 找到最大值
        int max = nums[0];
        for (int num : nums)
            max = Math.max(num, max);

        // 2. 对数组按照每个元素的每位进行计数排序
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(nums, exp);

        // 3. 寻找最大间隔
        int maxGap = 0;
        for (int i = 0; i < nums.length - 1; i++)
            maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);

        return maxGap;
    }

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

    // 桶排序
    // 时间复杂度：O(n)
    // 空间复杂度：O(2*b) 约等于 O(b) , b 是桶的个数
    public int maximumGap3(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        // 1. 找到最大最小值
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // 2. 初始化桶数组
        int bucketSize = Math.max(1, (max - min) / (nums.length - 1));
        int bucketNum = (max - min) / bucketSize + 1;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++)
            buckets[i] = new Bucket();

        // 3. 将所有元素添加到对应的桶中
        for (int num : nums) {
            int bucketIdx = (num - min) / bucketSize;
            buckets[bucketIdx].used = true;
            buckets[bucketIdx].min = Math.min(num, buckets[bucketIdx].min);
            buckets[bucketIdx].max = Math.max(num, buckets[bucketIdx].max);
        }

        // 4. 计算最大间隔
        int prevBucketMax = min;
        int maxGap = 0;
        for (Bucket bucket : buckets) {
            if (!bucket.used)
                continue;
            maxGap = Math.max(maxGap, bucket.min - prevBucketMax);
            prevBucketMax = bucket.max;
        }

        return maxGap;
    }

    private class Bucket {
        public boolean used;
        public int min = Integer.MAX_VALUE;
        public int max = Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 1};
        _164_MaximumGap maximumGap = new _164_MaximumGap();

        int maxGap = maximumGap.maximumGap3(nums);

        System.out.println(maxGap);
    }
}
