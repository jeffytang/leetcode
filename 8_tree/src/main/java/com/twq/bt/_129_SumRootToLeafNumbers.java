package com.twq.bt;

import java.util.List;

/**
 * 求根到叶子节点数字之和
 *
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 * 计算从根到叶子节点生成的所有数字之和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *     1
 *    / \
 *   2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 *
 * 输入: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 *
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 */
public class _129_SumRootToLeafNumbers {
    private int res = 0;

    // DFS
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return res;

        dfs(root, new StringBuilder());

        return res;
    }

    private void dfs(TreeNode node, StringBuilder sb) {
        if (node == null)
            return;
        sb.append(node.val);
        if (node.left == null && node.right == null)
            res += Integer.parseInt(sb.toString());

        dfs(node.left, sb);
        dfs(node.right, sb);

        sb.deleteCharAt(sb.length() - 1);
    }

    // 递归
    public int sumNumbers1(TreeNode root) {
        if (root == null)
            return 0;
        return sum(root, 0);
    }

    private int sum(TreeNode node, int cur) {
        if (node.left == null && node.right == null)
            return cur * 10 + node.val;

        int res = 0;
        if (node.left != null)
            res += sum(node.left, cur * 10 + node.val);
        if (node.right != null)
            res += sum(node.right, cur * 10 + node.val);

        return res;
    }
}
