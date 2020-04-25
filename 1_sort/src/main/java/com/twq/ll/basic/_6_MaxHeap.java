package com.twq.ll.basic;

//TODO
public class _6_MaxHeap<E extends Comparable<E>> extends AbstractSort<E> {

    // 根节点的索引是 1
    // 位置 k 的节点的父节点的位置是 k / 2
    // 两个子节点的位置是 2k 和 2k + 1
    private E[] array;
    private int size = 0;

    public _6_MaxHeap(int maxSize) {
        array = (E[])new Object[maxSize];
    }

    // 由下至上的堆有序化(上浮)
    private void swim(int k) {
        while (k > 1 && less(array[k / 2], array[k])) {
            swap(array, k / 2, k);
            k = k / 2;
        }
    }
    // 由上至下的堆有序化(下沉)
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(array[j], array[j + 1])) j++;
            if (!less(array[k], array[j])) break;
            swap(array, k, j);
            k = j;
        }
    }

    public void insert(E e) {
        array[++size] = e;
        swim(size);

    }

    public E delMax() {
        E max = array[1]; // 拿到最大的元素
        swap(array, 1, size - 1); // 交换第一个元素和最后一个元素
        array[size + 1] = null;  // 删除最后一个元素
        sink(1);    // 恢复堆的有序性
        return max;
    }

    @Override
    public void sort(E[] data) {
        // 建堆
        this.array = (E[])new Object[data.length + 1];
        for (E e : data) insert(e);

        // 排序，从大到小排序
        int i = 0;
        E max;
        while ((max = delMax()) != null) {
            data[i] = max;
            i++;
        }

    }


}
