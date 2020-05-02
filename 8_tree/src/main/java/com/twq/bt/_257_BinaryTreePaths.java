package com.twq.bt;

import com.twq.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的所有路径
 *
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 */
public class _257_BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root != null) helper(root, "", res);
        return res;
    }

    private void helper(TreeNode root, String path, List<String> ans) {
        if (root.left == null && root.right == null)
            ans.add(path + root.val);
        if (root.left != null)
            helper(root.left, path + root.val + "->", ans);
        if (root.right != null)
            helper(root.right, path + root.val + "->", ans);
    }
}
