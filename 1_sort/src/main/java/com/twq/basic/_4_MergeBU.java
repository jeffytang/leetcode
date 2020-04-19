package com.twq.basic;

/**
 *  自底向上归并排序
 * @param <E>
 */
public class _4_MergeBU<E extends Comparable<E>> extends AbstractSort<E> {

    private E[] aux;

    /**
     *  时间复杂度：O(nlogn)
     *  空间复杂度：O(n)
     * @param data
     */
    @Override
    public void sort(E[] data) {
        aux = (E[])new Object[data.length];

        for (int sz = 1; sz < data.length; sz = sz + sz) {
            for (int lo = 0; lo < data.length - sz; lo += sz + sz) {
                merge(data, lo, lo + sz - 1, Math.min(lo + sz + sz + 1, data.length - 1));
            }
        }
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
