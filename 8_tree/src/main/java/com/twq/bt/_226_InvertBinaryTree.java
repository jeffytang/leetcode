package com.twq.bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转一棵二叉树。
 *
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class _226_InvertBinaryTree {
    // 递归 1
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        // 翻转左子树
        TreeNode left = invertTree(root.left);
        // 翻转右子树
        TreeNode right = invertTree(root.right);

        // 翻转当前的节点
        root.left = right;
        root.right = left;

        return root;
    }

    // 递归 2
    public TreeNode invertTree1(TreeNode root) {
        if (root == null)
            return null;

        // 翻转左子树
        invertTree1(root.left);
        // 翻转右子树
        invertTree1(root.right);

        // 翻转当前的节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        return root;
    }

    private TreeNode bfs(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();

            // 翻转 curr 的左右子树
            TreeNode tmp = curr.left;
            curr.left = curr.right;
            curr.right = tmp;

            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }

        return root;
    }
}
