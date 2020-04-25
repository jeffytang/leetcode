package com.twq.sort;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 */
public class _215_KthLargestElementInAnArray {

    private Random random = new Random(System.currentTimeMillis());
    // 使用三路快排
    // 平均时间复杂度：O(n)，最坏 O(n^2)
    // 空间复杂度：O(1)
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int target = len - k;
        int left = 0, right = len - 1;
        while (true) {
            int index = partition(nums, left, right);
            if (index == target)
                return nums[target];
            else if (index < target)
                left = index + 1;
            else
                right = index - 1;
        }
    }


    private int partition(int[] nums, int left, int right) {
        if (right > left) {
            int randomIndex = left + 1 + random.nextInt(right - left);
            swap(nums, left, randomIndex);
        }

        int pivot = nums[left];
        int less = left;
        for (int k = left + 1; k <= right; k++) {
            // 小于 pivot 的元素都放到左边
            if (nums[k] < pivot) {
                less++;
                swap(nums, less, k);
            }
        }
        swap(nums, left, less);
        return less;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 使用优先队列
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(k)
    public int findKthLargest2(int[] nums, int k) {
        // 根据 k 的不同，选最大堆和最小堆，目的是让堆中的元素更小
        // 思路 1：k 要是更靠近 0 的话，此时 k 是一个较大的数，用最大堆
        // 例如在一个有 6 个元素的数组里找第 5 大的元素
        // 思路 2：k 要是更靠近 len 的话，用最小堆

        // 所以分界点就是 k = len - k
        int len = nums.length;
        if (k < len - k) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
            for (int i = 0; i < k; i++)
                minHeap.add(nums[i]);
            for (int i = k; i < len; i++) {
                Integer topEle = minHeap.peek();
                if (nums[i] > topEle) {
                    minHeap.remove();
                    minHeap.add(nums[i]);
                }
            }
            return minHeap.peek();
        } else {
            int capacity = len - k + 1;
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (a, b) -> b - a);
            for (int i = 0; i < capacity; i++)
                maxHeap.add(nums[i]);
            for (int i = capacity; i < len; i++) {
                Integer topEle = maxHeap.peek();
                if (nums[i] < topEle) {
                    maxHeap.remove();
                    maxHeap.add(nums[i]);
                }
            }
            return maxHeap.peek();
        }
    }
}
