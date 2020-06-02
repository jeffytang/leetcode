package com.twq.sort.basic;

/**
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素比较
 * 看是否满足大小关系要求。如果不满足就让它俩交换。
 *
 * 一次冒泡会让至少一个元素移动到它应该在的位置，重复 n 次，就完成了 n 个数据的排序工作
 * @param <E>
 */
public class _0_Bubble<E extends Comparable<E>> extends AbstractSort<E> {

    @Override
    public void sort(E[] data) {
        if (data == null || data.length <= 1) return;

        for (int i = 0; i < data.length; i++) {
            boolean hasSwap = false;
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (less(data[j + 1], data[j])) {
                    swap(data, j + 1, j);
                    hasSwap = true;
                }
            }
            // 如果当前这次冒泡操作已经没有数据交换时
            // 说明已经达到完全有序，不用再执行后续的冒泡操作
            if (!hasSwap) break;
        }
    }

    /*
        冒泡排序的稳定性：在冒泡排序中，只有交换才可以改变两个元素的前后顺序。为了保证冒泡排序算法的稳定性
                       当有相邻的两个元素大小相等的时候，我们做不交换，相同大小的数据在排序前后不会改变顺序
                       所以冒泡排序是稳定的排序
        时间复杂度：O(1)
        空间复杂度：
                最好的情况是，要排序的数组已经是有序的，只需要进行一次冒泡排序就可以了，时间复杂度 O(n)
                最坏的情况是，要排序的数组是倒序排列的，需要进行 n 次冒泡排序，时间复杂度 O(n ^ 2)
                平均的情况：O(n ^ 2)
     */

}
