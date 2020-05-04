package com.twq.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 */
public class _40_CombinationSum_ii {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return res;

        // 对数组进行排序，为了后面的去重
        Arrays.sort(candidates);

        findCombinationSum(candidates, target, 0, new ArrayList<>());
        return res;
    }

    /**
     *
     * @param candidates
     * @param target
     * @param start    作用是为了去重，
     *                 重复的原因是在较深层的结点值考虑了之前考虑过的元素，
     *                 因此我们需要设置“下一轮搜索的起点”即可
     * @param combination
     */
    private void findCombinationSum(int[] candidates,
                                    int target,
                                    int start,
                                    List<Integer> combination) {
        if (target < 0)
            return;

        if (target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 去重
            if (i > start && candidates[i - 1] == candidates[i]) {
                continue;
            }
            combination.add(candidates[i]);
            // 从候选数组的当前索引值的下一位开始。
            findCombinationSum(candidates, target - candidates[i], i + 1, combination);
            combination.remove(combination.size() - 1);
        }
    }
}
