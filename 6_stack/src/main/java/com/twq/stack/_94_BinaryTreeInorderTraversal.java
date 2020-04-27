package com.twq.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 题目：二叉树的中序遍历
 *
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class _94_BinaryTreeInorderTraversal {
    // 递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    private void inOrder(TreeNode node, List<Integer> res) {
        if (node == null) return;

        inOrder(node.left, res);
        res.add(node.val);
        inOrder(node.right, res);
    }

    // 迭代
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // 先访问左节点
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // 再访问根节点
            curr = stack.pop();
            res.add(curr.val);
            // 最后访问右节点
            curr = curr.right;
        }
        return res;
    }
}
