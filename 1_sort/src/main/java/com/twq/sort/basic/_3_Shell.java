package com.twq.sort.basic;

/**
 * 对于大规模乱序数组插入排序很慢，因为它只会交换相邻的元素，因此元素只能一点一点地从数组的一段移动到另一端
 * 例如：如果最小的元素正好在数组的尽头，要将它挪动到正确的位置就需要 N-1 次移动。
 *
 * 希尔排序为了加快速度简单地改进了插入排序，交换不相邻的元素对数组的局部进行排序，并最终用插入排序将局部有序的数组排序
 *
 * 希尔排序的思想是使数组中任意间隔为 h 的元素都是有序的。这样的数组被称为 h 有序数组
 * @param <E>
 */
public class _3_Shell<E extends Comparable<E>> extends AbstractSort<E> {

    @Override
    public void sort(E[] data) {
        int n = data.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093 ...

        while (h >= 1) {
            // 将数组变为 h 有序
            for (int i = h; i < n; i++) {
                // 将 data[i] 插入到 data[i - h]、data[i - 2 * h]、data[i - 3 * h]...之中
                // 保证 data[i - h]、data[i - 2 * h]、data[i - 3 * h]... 有序
                for (int j = i; j >= h && less(data[j], data[j - h]); j -= h)
                    swap(data, j, j - h);
            }
            h /= 3;
        }
    }
}
