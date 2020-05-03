package com.twq.bt;

import java.util.*;

/**
 * 全排列 II
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 */
public class _47_Permutations_ii {
    List<List<Integer>> res = new ArrayList<>();
    boolean[] used;

    // 回溯算法
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);

        used = new boolean[nums.length];
        generatePermutation(nums, new ArrayDeque<>());
        return res;
    }

    // 路径：记录在 permutes 中
    // 选择列表：nums 中不存在于 permutes 中的那些元素
    // 结束条件：nums 中的元素全部在 permutes 中
    private void generatePermutation(int[] nums, Deque<Integer> permutes) {
        // 触发结束条件
        if (permutes.size() == nums.length) {
            res.add(new ArrayList<>(permutes));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (used[i]) continue;

            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // 做选择
            permutes.addLast(nums[i]);
            used[i] = true;
            // 进入下一层决策树
            generatePermutation(nums, permutes);
            // 取消选择
            permutes.removeLast();
            used[i] = false;
        }
    }
}
