package com.twq.design;

import java.util.PriorityQueue;

/**
 * 数据流的中位数
 *
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *      1. void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 *      2. double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 进阶:
 *      1. 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 *      2. 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 */
public class _295_FindMedianFromDataStream {
    // 存储大于大顶堆中的最大值的所有值
    private PriorityQueue<Integer> minHeap;
    // 存储小于小顶堆中的最小值的所有值
    private PriorityQueue<Integer> maxHeap;

    public _295_FindMedianFromDataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    }

    public void addNum(int num) {
        maxHeap.add(num);

        minHeap.add(maxHeap.remove());

        // 保证大顶堆中数据的个数大于等于小顶堆
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        }
    }

    public double findMedian() {
        // 如果大顶堆的个数大于小顶堆，说明有奇数个数据，直接取大顶堆的堆顶数据即可
        return maxHeap.size() > minHeap.size() ?
                maxHeap.peek() : (maxHeap.peek() + minHeap.peek()) * 0.5;
    }
}
