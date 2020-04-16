package com.twq.basic;

/**
 *  自顶向下归并排序
 * @param <E>
 */
public class _4_Merge<E extends Comparable<E>> extends AbstractSort<E> {

    private E[] aux;

    /**
     *  时间复杂度：O(nlogn)
     *  空间复杂度：O(n)
     * @param data
     */
    @Override
    public void sort(E[] data) {
        aux = (E[])new Object[data.length];
        sort(data, 0, data.length);
    }

    public void sort(E[] data, int lo, int hi) {
        if (lo > hi) return;
        int mid = lo + (hi - lo) / 2;
        // 将左边排序
        sort(data, lo, mid);
        // 将右边排序
        sort(data, mid + 1, hi);
        // 合并有序的左右边
        merge(data, lo, mid, hi);
    }

    public void merge(E[] a, int lo, int mid, int hi) {
        // 将 a[lo...mid] 和 a[mid...hi] 进行合并
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}
