package com.twq.bt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 全排列
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/permutations
 */
public class _64_Permutations {
    List<List<Integer>> res = new ArrayList<>();
    boolean[] used;

    // 回溯算法
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
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
