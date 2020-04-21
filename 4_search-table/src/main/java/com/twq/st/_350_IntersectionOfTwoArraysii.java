package com.twq.st;

import java.util.*;

/**
 *  给定两个数组，编写一个函数来计算它们的交集。
 *
 *  输入: nums1 = [1,2,2,1], nums2 = [2,2]
 *  输出: [2,2]
 *
 *  输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *  输出: [4,9]
 *
 *  说明：
 *      1. 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 *      2. 我们可以不考虑输出结果的顺序。
 *
 *  进阶：
 *      1. 如果给定的数组已经排好序呢？你将如何优化你的算法？
 *      2. 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 *          将 nums1 作为 counter，然后扫描 nums2
 *      3. 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *          使用外部排序，参考：https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/82243/Solution-to-3rd-follow-up-question
 */
public class _350_IntersectionOfTwoArraysii {
    // Map
    public int[] intersect1(int[] nums1, int[] nums2) {
        // 使用 Map 记录数字出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1)
            map.put(num, map.getOrDefault(num, 0) + 1);

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.getOrDefault(num, 0) > 0) {
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);

        return res;
    }

    // 进阶 1 ：如果给定的数组已经排好序呢？你将如何优化你的算法？
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                nums1[k] = nums1[i];
                i++;
                k++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            }
        }

        return Arrays.copyOfRange(nums1, 0, k);
    }
}
