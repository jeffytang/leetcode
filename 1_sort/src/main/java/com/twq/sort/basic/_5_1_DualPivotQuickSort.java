package com.twq.sort.basic;

import java.util.Arrays;
import java.util.Random;

/**
 *  双轴快排
 *
 *  双轴快排对比单轴快排：https://www.jianshu.com/p/2c6f79e8ce6e
 */
public class _5_1_DualPivotQuickSort<E extends Comparable<E>> extends AbstractSort<E> {

    @Override
    public void sort(E[] data) {
        sort(data, 0, data.length - 1);
    }

    private void sort(E[] data, int left, int right) {
        if (left >= right) {
            return;
        }
        // 分区
        PartitionSegment partitionSegment = new PartitionSegment(data, left, right).partition();
        int less = partitionSegment.getLess();
        int great = partitionSegment.getGreat();

        sort(data, left, less - 1);
        sort(data, less + 1, great - 1);
        sort(data, great + 1, right);
    }

    private class PartitionSegment {
        private E[] data;
        private int left;
        private int right;

        private int less;
        private int great;

        public PartitionSegment(E[] data, int left, int right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public int getLess() {
            return less;
        }

        public int getGreat() {
            return great;
        }

        public PartitionSegment partition() {
            /*
             * Partitioning:
             *
             *   left part           center part                   right part
             * +--------------------------------------------------------------+
             * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
             * +--------------------------------------------------------------+
             *               ^                          ^       ^
             *               |                          |       |
             *              less                        k     great
             *
             * Invariants:
             *
             *              all in (left, less)   < pivot1
             *    pivot1 <= all in [less, k)     <= pivot2
             *              all in (great, right) > pivot2
             *
             * Pointer k is the first index of ?-part.
             */

            if (bigger(data[left], data[right])) {
                swap(data, left, right);
            }

            E pivot1 = data[left]; // 取小值
            E pivot2 = data[right]; // 取大值

            less = left;
            great = right;
            int k = left + 1;

            OUT_LOOP:
            while (k < great) {
                if (less(data[k], pivot1)) {
                    less++;
                    swap(data, less, k);
                    k++;
                } else if (less(data[k], pivot2)) {
                    k++;
                } else {
                    // 一直找到小于等于 pivot2 的元素
                    while (bigger(data[--great], pivot2)) {
                        if (great <= k) {
                            break OUT_LOOP;
                        }
                    }
                    if (less(data[great], pivot1)) {
                        swap(data, great, k);
                        less++;
                        swap(data, less, k);
                    } else {
                        swap(data, great, k);
                    }
                    k++;
                }
            }

            swap(data, left, less);
            swap(data, right, great);
            return this;
        }
    }

    public static void main(String[] args) {
        //待排序数组有30个元素
        Integer[] arr = new Integer[30];

        //随机数类
        Random random = new Random();

        //初始化生成100以内的随机数
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        System.out.println("双轴快排前：" + Arrays.toString(arr));

        //调用双轴快速排序算法
        new _5_1_DualPivotQuickSort<Integer>().sort(arr);

        System.out.println("双轴快排后：" + Arrays.toString(arr));
    }
}
