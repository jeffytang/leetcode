package com.twq.basic;

public class _5_Quick<E extends Comparable<E>> extends AbstractSort<E> {

    @Override
    public void sort(E[] data) {
        sort(data, 0, data.length);
    }

    private void sort(E[] data, int lo, int hi) {
        if (lo > hi) return;
        int pivot = partition(data, lo, hi);
        sort(data, lo, pivot - 1);
        sort(data, pivot + 1, hi);
    }

    private int partition(E[] a, int lo, int hi) {
        // 将数组 a[lo...hi] 切分为 a[lo...i - 1], a[i], a[i + 1...hi]
        int i = lo, j = hi + 1;
        // 切分元素
        E v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }
}
