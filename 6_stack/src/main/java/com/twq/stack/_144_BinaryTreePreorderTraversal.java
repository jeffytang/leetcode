package com.twq.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 题目：二叉树的前序遍历
 *
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 *
 * 进阶：
 *  递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class _144_BinaryTreePreorderTraversal {
    // 递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    private void preOrder(TreeNode node, List<Integer> res) {
        if (node == null)
            return;

        res.add(node.val);
        preOrder(node.left, res);
        preOrder(node.right, res);
    }

    // 迭代
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 1. 先访问根节点
            res.add(node.val);
            // 3. 最后访问右节点
            if (node.right != null) stack.push(node.right);
            // 2. 再访问左节点
            if (node.left != null) stack.push(node.left);
        }
        return res;
    }
}
