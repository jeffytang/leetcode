package com.twq.sort.basic;

/**
 *  插入排序：将每个元素插入到已经有序的数组中的适当位置
 *
 *  插入排序对于部分有序的数组十分高效，也适合小规模数组
 *
 * 插入排序的核心思想：取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入
 * 并保证已排序区间数据一直有序。重复这个过程，直到未排序区间中元素为空，算法结束
 *
 * 为什么插入排序比冒泡排序更加的受欢迎？
 * 答：从代码的实现上看，冒泡排序的数据交换要比插入排序的数据移动复杂
 * 冒泡排序需要 3 个赋值操作，而插入排序只需要 1 个
 * 所以插入排序的性能会比冒泡排序的要好
 * @param <E>
 */
public class _2_1_Insertion<E extends Comparable<E>> extends AbstractSort<E> {

    /**
     * 原始数组         34 8 64 51 32 21    移动的位置
     * p = 1 趟之后     8 34 64 51 32 21       1
     * p = 2 趟之后     8 34 64 51 32 21       0
     * p = 3 趟之后     8 34 51 64 32 21       1
     * p = 4 趟之后     8 32 34 51 64 21       3
     * p = 5 趟之后     8 21 32 34 51 64       4
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param data
     */
    @Override
    public void sort(E[] data) {
        if (data == null || data.length <= 1) return;

        int n = data.length;
        for (int i = 0; i < n; i++) {
            // 将 data[i] 插入到 a[i-1]、a[i-2]、a[i-3].... 中
            for (int j = i; j > 0; j--)
                if (less(data[j], data[j - 1]))
                    swap(data, j, j -1);
        }
    }

    // 减少交换的次数，从而提高插入排序的速度
    public void sort1(E[] data) {
        if (data == null || data.length <= 1) return;

        int n = data.length;
        int j;
        for (int p = 1; p < n; p++) {
            E tmp = data[p];
            // 将较大的元素总是向右移动，从而减少交换的次数，从而可以提高速度
            // 原因：减少交换的次数，就可以减少访问数组的次数(交换元素需要访问两次数组)

            // 找到 p 对应的元素需要插入的位置
            for (j = p; j > 0; j--)
                if (less(tmp, data[j - 1]))
                    data[j] = data[j - 1];
            data[j] = tmp;
        }
        /*
            稳定性：对于值相同的元素，我们可以选择将后面出现的元素，插入到前面出现元素的后面
                    这样就可以保持原有的前后顺序不变，所以插入排序是稳定的排序
            时间复杂度：O(1)
            空间复杂度：
                    最好的情况是，要排序的数组已经是有序的，时间复杂度 O(n)
                    最坏的情况是，要排序的数组是倒序排列的，时间复杂度 O(n ^ 2)
                    平均的情况：O(n ^ 2)
        */
    }

    public static void main(String[] args) {
        new _2_1_Insertion<Integer>().sort1(new Integer[]{34, 8, 64, 51, 32, 21});
    }
}
