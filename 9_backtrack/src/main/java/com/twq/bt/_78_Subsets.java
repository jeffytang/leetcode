package com.twq.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/subsets
 */
public class _78_Subsets {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0)
            return res;
        findSubset(nums, 0, new ArrayList<>());
        return res;
    }

    private void findSubset(int[] nums, int start, List<Integer> subset) {
        res.add(new ArrayList<>(subset));

        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            findSubset(nums, i + 1, subset);
            subset.remove(subset.size() - 1);
        }
    }
}
