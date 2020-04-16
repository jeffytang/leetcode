package com.twq.basic;

/**
 *  算法：
 *      首先，找到数组中最小的那个元素
 *      其次，将它和数组的第一个元素交换位置，(如果第一个元素就是最小元素那么它就和自己交换)
 *      再次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置
 *      如此往复，直到将整个数组排序
 *  之所以叫做选择排序，因为它在不断地选择剩余元素之中的最小者
 * @param <E>
 */
public class _1_Selection<E extends Comparable<E>> extends AbstractSort<E> {

    /**
     *  时间复杂度：O(n^2)
     *  空间复杂度：O(1)
     * @param data
     */
    @Override
    public void sort(E[] data) {
        int n = data.length;
        for (int i = 0; i < n; i++) {
            // 将 data[i] 和 data[i+1...n] 中最小的元素交换
            int min = i;    // 最小元素的索引
            for (int j = i + 1; j < n; j++)
                if (less(data[j], data[min])) min = j;
            swap(data, i, min);
        }
    }
}
