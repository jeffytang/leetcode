package com.twq.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/combinations
 */
public class _77_Combinations {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n < 0 || k < 0 || n < k)
            return res;
        findCombination(n, k, 1, new ArrayList<>());

        return res;
    }

    private void findCombination(int n,
                                 int k,
                                 int start,
                                 List<Integer> combinations) {
        if (combinations.size() == k) {
            res.add(new ArrayList<>(combinations));
            return;
        }
        // 这里的条件是可以： i <= n
        // 还有种优化的剪枝策略
        // 到这里后还有 k - combinations.size() 个空位需要选择数字
        // 所以我们需要在 [i...n] 中至少要找到 k - combinations.size() 个元素就可以，没必要找到更多的元素
        // 所以 i <= n - (k - combinations.size()) + 1
        for (int i = start; i <= n - (k - combinations.size()) + 1; i++) {
            combinations.add(i);
            findCombination(n, k, i + 1, combinations);
            combinations.remove(combinations.size() - 1);
        }
    }
}
