package com.twq.sort.basic;

import java.util.Arrays;

public class _5_Quick<E extends Comparable<E>> extends AbstractSort<E> {

    private class PivotSegment {
        public int less;
        public int great;

        public PivotSegment(int less, int great) {
            this.less = less;
            this.great = great;
        }
    }

    @Override
    public void sort(E[] data) {
        sort(data, 0, data.length - 1);
    }

    private void sort(E[] data, int lo, int hi) {
        if (lo > hi) return;
        PivotSegment segment = partition(data, lo, hi);
        sort(data, lo, segment.less - 1);
        sort(data, segment.great + 1, hi);
    }
    // 应用场景：
    // 1. leetcode 75
    // 2. leetcode 215
    private PivotSegment partition(E[] a, int lo, int hi) {
        /*
         * Partitioning degenerates to the traditional 3-way
         * (or "Dutch National Flag") schema:
         *
         *   left part    center part              right part
         * +-------------------------------------------------+
         * |  < pivot  |   == pivot   |     ?    |  > pivot  |
         * +-------------------------------------------------+
         *              ^              ^        ^
         *              |              |        |
         *             less            k      great
         *
         * Invariants:
         *
         *   all in (left, less)   < pivot
         *   all in [less, k)     == pivot
         *   all in (great, right) > pivot
         *
         * Pointer k is the first index of ?-part.
         */
        E pivot = a[lo];

        int less = lo, great = hi;
        int k = less;
        for (; k <= great; ++k) {
            E ak = a[k];
            if (ak == pivot) continue;

            if (less(ak, pivot)) { // Move a[k] to left part
                a[k] = a[less];
                a[less] = ak;
                less++;
            } else { // a[k] > pivot - Move a[k] to right part
                while (bigger(a[great], pivot))
                    great--;
                if (less(a[great], pivot)) { // a[great] <= pivot
                    a[k] = a[less];
                    a[less] = a[great];
                    less++;
                } else { // a[great] == pivot
                    /*
                     * Even though a[great] equals to pivot, the
                     * assignment a[k] = pivot may be incorrect,
                     * if a[great] and pivot are floating-point
                     * zeros of different signs. Therefore in float
                     * and double sorting methods we have to use
                     * more accurate assignment a[k] = a[great].
                     */
                    a[k] = a[great];
                }
                a[great] = ak;
                great--;
            }
        }
        return new PivotSegment(less, great);
    }

    public static void main(String[] args) {
        _5_Quick quick = new _5_Quick();
        Integer[] data = {23, 1, 2, 1, 4, 2, 55, 22};
        quick.sort(data);

        System.out.println(Arrays.toString(data));
    }
}
