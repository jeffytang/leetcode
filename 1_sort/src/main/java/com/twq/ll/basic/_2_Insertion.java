package com.twq.ll.basic;

/**
 *  插入排序：将每个元素插入到已经有序的数组中的适当位置
 *
 *  插入排序对于部分有序的数组十分高效，也适合小规模数组
 * @param <E>
 */
public class _2_Insertion<E extends Comparable<E>> extends AbstractSort<E> {

    /**
     * 原始数组         34 8 64 51 32 21    移动的位置
     * p = 1 趟之后     8 34 64 51 32 21       1
     * p = 2 趟之后     8 34 64 51 32 21       0
     * p = 3 趟之后     8 34 51 64 32 21       1
     * p = 4 趟之后     8 32 34 51 64 21       3
     * p = 5 趟之后     8 21 32 34 51 64       4
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param data
     */
    @Override
    public void sort(E[] data) {
        int n = data.length;
        for (int i = 0; i < n; i++) {
            // 将 data[i] 插入到 a[i-1]、a[i-2]、a[i-3].... 中
            for (int j = i; j > 0 && less(data[j], data[j - 1]); j--)
                swap(data, j, j -1);
        }
    }

    // 减少交换的次数，从而提高插入排序的速度
    public void sort1(E[] data) {
        int n = data.length;
        int j;
        for (int p = 1; p < n; p++) {
            E tmp = data[p];
            // 将较大的元素总是向右移动，从而减少交换的次数，从而可以提高速度
            // 原因：减少交换的次数，就可以减少访问数组的次数(交换元素需要访问两次数组)
            for (j = p; j > 0 && less(tmp, data[j - 1]); j--)
                data[j] = data[j - 1];
            data[j] = tmp;
        }
    }
}
