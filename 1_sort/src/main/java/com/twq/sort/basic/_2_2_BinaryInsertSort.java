package com.twq.sort.basic;

/**
 *  二分插入排序，是对插入排序的一种优化
 *  如果数据量很少的话，可以使用这种排序。
 *  如果给定的数组的前面已经是排好序的话，使用这种排序算法就更加有效了。
 *
 *  算法：
 *      1. 从数组的开始往后寻找到一段升序的数组，如果这段数组是降序的话，那么将其反转为升序
 *      2. 返回这段升序数组的长度 runLen，这样 [0, runLen) 是升序的
 *      3. 依次从 [runLen, length) 中拿出一个元素插入到  [0, runLen) 所应该在的位置上
 *
 *  这个排序需要 O(nlogn) 次比较，元素移动最差为 O(n^2)
 * @param <E>
 */
public class _2_2_BinaryInsertSort<E extends Comparable<E>> extends AbstractSort<E> {

    @Override
    public void sort(E[] data) {
        // 找到一个位置 runLen，使得 [0, runLen] 之间的元素是升序的
        int runLen = countRunAndMakeAscending(data, 0, data.length - 1);
        // 进行二分插入排序
        binaryInsertSort(data, 0, data.length - 1, runLen);
    }

    private void binaryInsertSort(E[] data, int lo, int hi, int start) {
        assert lo <= start && start <= hi;
        if (start == lo) {
            start++;
        }

        // 通过二分查找每一个没有排序的元素在前面排序元素的位置
        // 并且插入
        for (; start < hi; start++) {
            E pivot = data[start];

            // Set left (and right) to the index where a[start] (pivot) belongs
            int left = lo;
            int right = start;
            assert left <= right;

            /*
             * Invariants:
             *   pivot >= all in [lo, left).
             *   pivot <  all in [right, start).
             */
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (less(pivot, data[mid]))
                    right = mid;
                else
                    left = mid + 1;
            }

            assert left == right;

            /*
             * The invariants still hold: pivot >= all in [lo, left) and
             * pivot < all in [left, start), so pivot belongs at left.  Note
             * that if there are elements equal to pivot, left points to the
             * first slot after them -- that's why this sort is stable.
             * Slide elements over to make room for pivot.
             */
            int n = start - left; // The number of elements to move
            // Switch is just an optimization for arraycopy in default case
            switch (n) {
                case 2: data[left + 2] = data[left + 1];
                case 1: data[left + 1] = data[left];
                    break;
                default: System.arraycopy(data, left, data, left + 1, n);
            }

            data[left] = pivot;
        }
    }

    /**
     *  A run is the longest ascending sequence with:
     *      a[lo] <= a[lo + 1] <= a[lo + 2] <= ...
     *  or the longest descending sequence with:
     *      a[lo] >  a[lo + 1] >  a[lo + 2] >  ...
     *
     *  返回数组 data 的 [lo, hi] 区间中从 lo 开始 run 的长度
     *  如果 run 是降序的，那么将其转化为升序
     *
     *  也就是说 [lo, lo+run] 这段区间的元素是升序的
     * @param data
     * @param lo
     * @param hi
     * @return
     */
    private int countRunAndMakeAscending(E[] data, int lo, int hi) {
        assert lo < hi;
        int runHi = lo + 1;
        if (runHi == hi)
            return 1;

        // Find end of run, and reverse range if descending
        if (less(data[runHi++], data[lo])) { // Descending
            while (runHi < hi && less(data[runHi], data[runHi - 1]))
                runHi++;
            reverseRange(data, lo, runHi);
        } else { // Ascending
            while (runHi < hi && biggerAndEqual(data[runHi], data[runHi - 1])) {
                runHi++;
            }
        }

        return runHi - lo;
    }

    private void reverseRange(E[] data, int lo, int hi) {
        hi--;
        while (lo < hi) {
            E e = data[lo];
            data[lo++] = data[hi];
            data[hi--] = e;
        }
    }
}
