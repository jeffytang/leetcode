package com.twq.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  组合总和 III
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 */
public class _216_CombinationSum_iii {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k < 0 || n < 0)
            return res;


        findCombinationSum(k, n, 1, new ArrayList<>());
        return res;
    }


    private void findCombinationSum(int k,
                                    int n,
                                    int start,
                                    List<Integer> combination) {
        if (n < 0)
            return;

        if (n == 0 && combination.size() == k) {
            res.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i <= 9; i++) {
            combination.add(i);
            findCombinationSum(k, n - i, i + 1, combination);
            combination.remove(combination.size() - 1);
        }
    }

    public static void main(String[] args) {
        _216_CombinationSum_iii i = new _216_CombinationSum_iii();
        System.out.println(i.combinationSum3(3, 7));
    }
}
