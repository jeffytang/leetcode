package com.twq.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集 II
 *
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 */
public class _90_Subsets_ii {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0)
            return res;
        // 排序，为了后面的去重
        Arrays.sort(nums);
        findSubset(nums, 0, new ArrayList<>());
        return res;
    }

    private void findSubset(int[] nums, int start, List<Integer> subset) {
        res.add(new ArrayList<>(subset));

        for (int i = start; i < nums.length; i++) {
            // 去重
            if (i > start && nums[i - 1] == nums[i]) continue;
            subset.add(nums[i]);
            findSubset(nums, i + 1, subset);
            subset.remove(subset.size() - 1);
        }
    }
}
