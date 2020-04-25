package com.twq.sort.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *  桶排序的步骤：
 *      1. 创建若干个桶 (桶与桶之间是有顺序的)
 *      2. 将每个元素按照一定的规则放到对应的桶中
 *      3. 对每个桶中的元素进行排序
 *      4. 拿到所有桶中排好序的元素
 *
 *  桶排序适合用于：
 *      1. 具有一定范围的并且分布均匀的输入
 * @param <E>
 */
public class _7_Bucket<E extends Comparable<E>> extends AbstractSort<E> {

    // 用于计算指定元素所在的 bucket 的 Index
    private BucketIndexCal<E> bucketIndexCal;

    public _7_Bucket(BucketIndexCal<E> bucketIndexCal) {
        this.bucketIndexCal = bucketIndexCal;
    }

    /**
     *  时间复杂度：
     *      1. 最坏 O(n^2)
     *      2. 最好 O(n + k)
     *      3. 平均 O(n)
     *
     *  不考虑负数
     *  考虑负数的参考：https://www.geeksforgeeks.org/bucket-sort-to-sort-an-array-with-negative-numbers/
     * @param data
     */
    @Override
    public void sort(E[] data) {
        int n = data.length;

        // 1. 创建空的 buckets
        ArrayList<E>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++)
            buckets[i] = new ArrayList<>();

        // 2. 将所有的元素添加进对应的 bucket
        for (int i = 0; i < n; i++) {
            int index = bucketIndexCal.cal(data[i]);
            buckets[index].add(data[i]);
        }

        // 3. 对每一个 bucket 中的元素进行排序
        for (int i = 0; i < n; i++)
            Collections.sort(buckets[i]);

        // 4. 从 buckets 中拿到排序后的数组
        int index = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < buckets[i].size(); j++)
                data[index++] = buckets[i].get(j);

    }

    public static void main(String[] args) {
        Float[] arr = { 0.42F, 0.32F, 0.33F, 0.52F, 0.37F, 0.47F, 0.51F };

        _7_Bucket<Float> bucket = new _7_Bucket<>(e -> (int) (e * arr.length));

        bucket.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
