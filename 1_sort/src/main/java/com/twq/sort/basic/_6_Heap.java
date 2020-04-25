package com.twq.sort.basic;

/**
 * 这个堆排序的特点：
 * 1. 时间复杂度是 O(nlogn)
 * 2. 空间复杂度是 O(1)
 * @param <E>
 */
public class _6_Heap<E extends Comparable<E>> extends AbstractSort<E> {

    @Override
    public void sort(E[] data) {
        // 1. 建堆，从右往左建堆
        for (int i = data.length / 2; i >= 0; i--)
            sink(data, i, data.length);

        // 2. 删除最大值的过程中对数组进行排序
        for (int i = data.length - 1; i > 0; i--) {
            swap(data, 0, i);
            sink(data, 0, i);
        }
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * 使用插入排序对根为 i 的子堆进行排序(或者说建堆)
     * @param data 数组
     * @param i
     * @param n
     */
    private void sink(E[] data, int i, int n) {
        int child;
        E tmp;

        for (tmp = data[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            // 找到两个子节点中最大的那个节点
            if (child != n - 1 && less(data[child], data[child + 1]))
                child++;
            // 如果根节点小于子节点中最大的节点，则将最大的子节点上升为父节点
            if (less(tmp, data[child]))
                data[i] = data[child];
            else
                break;
        }

        data[i] = tmp;
    }
}
