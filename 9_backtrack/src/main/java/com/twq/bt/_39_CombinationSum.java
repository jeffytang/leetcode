package com.twq.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *      所有数字（包括 target）都是正整数。
 *      解集不能包含重复的组合。
 *
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
public class _39_CombinationSum {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return res;
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
            combination.add(candidates[i]);
            // 还从候选数组的当前索引值开始。
            findCombinationSum(candidates, target - candidates[i], i, combination);
            combination.remove(combination.size() - 1);
        }
    }
}
