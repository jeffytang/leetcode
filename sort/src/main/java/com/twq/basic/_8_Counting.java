package com.twq.basic;

import java.util.Arrays;

/**
 *  计数排序演示：https://www.cs.usfca.edu/~galles/visualization/CountingSort.html
 *
 *  参考：
 *      https://www.programiz.com/dsa/counting-sort
 *      https://www.geeksforgeeks.org/counting-sort/
 *
 */
public class _8_Counting {

    /**
     *  时间复杂度：O(n + k) n 表示输入数组的长度，k 表示输入的范围
     *  空间复杂度：O(n + k)
     *
     *  注意：此算法不能对负数进行排序
     * @param data
     */
    public void sort(int[] data) {
        // 1. 找到数组中最大值
        int max = data[0];
        for (int num : data)
            if (num > max)
                max = num;

        // 2. 初始化一个计数数组
        int[] count = new int[max + 1];

        // 3. 对数组中所有的元素进行计数
        for (int i = 0; i < data.length; i++)
            count[data[i]]++;

        // 4. 累计计算元素出现的次数
        for (int i = 1; i <= max; i++)
            count[i] += count[i - 1];

        // 5. 在计数数组中找到每个元素排序后的 index
        // 并放入排序后的数组中
        int[] output = new int[data.length];
        for (int i = data.length - 1; i >= 0; i--) {
            output[count[data[i]] - 1] = data[i];
            count[data[i]]--;
        }

        // 6. 拷贝数组
        for (int i = 0; i < data.length; i++)
            data[i] = output[i];
    }

    // 可以对负数进行排序
    public void sort2(int[] data) {
        int max = data[0];
        int min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) max = data[i];
            if (data[i] < min) min = data[i];
        }

        int range = max - min + 1;
        int[] count = new int[range + 1];

        for (int i = 0; i < data.length; i++)
            count[data[i] - min]++;

        for (int i = 1; i <= range; i++)
            count[i] += count[i - 1];

        int[] output = new int[data.length];
        for (int i = data.length - 1; i >= 0; i--) {
            output[count[data[i] - min] - 1] = data[i];
            count[data[i] - min]--;
        }

        for (int i = 0; i < data.length; i++)
            data[i] = output[i];
    }

    public static void main(String[] args) {
        _8_Counting counting = new _8_Counting();

        int[] data = { 4, -2, 2, 8, 3, 3, 1 };
        counting.sort2(data);
        System.out.println(Arrays.toString(data));
    }
}
