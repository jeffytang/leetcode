package com.twq.bs;

/**
 *  输入: nums = [1,2,3,1]
 *  输出: 2
 *  解释: 3 是峰值元素，你的函数应该返回其索引 2。
 *
 *  输入: nums = [1,2,1,3,5,6,4]
 *  输出: 1 或 5
 *  解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *          或者返回索引 5， 其峰值元素为 6。
 *
 */
public class _162_FindPeakElement {
    // 1. 线性扫描
    public int findPeakElement1(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        for (int i = 0; i < nums.length - 1; i++)
            if (nums[i] > nums[i + 1])
                return i;

        return nums.length - 1;
    }

    // 第一种二分查找
    public int findPeakElement2(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        int l = 0, r = nums.length - 1; // 这里必须要减 1，因为下面需要访问 mid + 1 的元素
        while (l < r) {
            // 从数组 nums 中找到中间的元素 mid
            int mid = l + (r - l) / 2;
            // 若该元素恰好位于降序序列或者一个局部下降坡度中
            //      （通过将 nums[i] 与右侧比较判断)，则说明峰值会在本元素的左边
            // 于是，我们将搜索空间缩小为 mid 的左边(包括其本身)，并在左侧子数组上搜索。
            if (nums[mid] > nums[mid + 1])
                r = mid;
            // 若该元素恰好位于升序序列或者一个局部上升坡度中
            //      （通过将 nums[i] 与右侧比较判断)，则说明峰值会在本元素的右边。
            // 于是，我们将搜索空间缩小为 mid 的右边，并在右侧子数组上搜索
            else
                l = mid + 1;
        }

        return l;
    }

    // 第二种二分查找
    public int findPeakElement3(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        int l = 0, r = nums.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid;
        }

        // 需要对两个元素进行对比
        return nums[l] > nums[r] ? l : r;
    }
}
