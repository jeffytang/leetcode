package com.twq.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径总和 II
 *
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 */
public class _113_PathSum_ii {
    // DFS
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        List<Integer> path = new ArrayList<>();
        dfs(root, sum, list, path);
        return list;
    }

    private void dfs(TreeNode node, int target,
                     List<List<Integer>> ans, List<Integer> path) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null && node.val == target) {
            ans.add(new ArrayList<>(path));
        }

        dfs(node.left, target - node.val, ans, path);
        dfs(node.right, target - node.val, ans, path);

        // 回溯
        path.remove(path.size() - 1);
    }
}
