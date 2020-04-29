package com.twq.queue.priority;

import java.util.*;

/**
 * 前 K 个高频元素
 * <p>
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 提示：
 * 1. 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 2. 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 3. 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 4. 你可以按任意顺序返回答案。
 *
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements/
 */
public class _347_TopKFrequentElements {
    // 优先队列
    // 时间复杂度：O(nlogk)
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 对每个元素计数
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums)
            count.put(num, count.getOrDefault(num, 0) + 1);

        int[] res = new int[k];

        PriorityQueue<Map.Entry<Integer, Integer>> queue = null;

        // 2. 将 count 中的元素放入到最小堆中
        // 在堆中按照元素出现的次数进行排序
        queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.getValue()));
        for (Map.Entry<Integer, Integer> entry : count.entrySet())
            if (queue.size() < k)
                queue.add(entry);
            else {
                if (queue.peek().getValue() < entry.getValue()) {
                    queue.remove();
                    queue.add(entry);
                }

            }

        int i = 0;
        while (!queue.isEmpty())
            res[i++] = queue.remove().getKey();

        return res;
    }

    // 桶排序思想
    // 时间复杂度：O(n)
    public int[] topKFrequent1(int[] nums, int k) {
        // 1. 对每个元素计数
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int num : nums)
            numCount.put(num, numCount.getOrDefault(num, 0) + 1);

        // 创建 nums.length + 1 个 bucket
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            Integer count = entry.getValue();
            if (buckets[count] == null)
                buckets[count] = new ArrayList<>();
            buckets[count].add(entry.getKey());
        }

        // 从桶中获取结果，从后往前遍历
        int[] res = new int[k];
        int j = 0;
        for (int i = buckets.length - 1; i >= 0 && j < k; i--) {
            if (buckets[i] == null) continue;
            for (int num : buckets[i])
                res[j++] = num;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        _347_TopKFrequentElements e = new _347_TopKFrequentElements();
        System.out.println(Arrays.toString(e.topKFrequent1(nums, 2)));
    }
}
