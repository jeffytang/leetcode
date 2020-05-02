package com.twq.bst;

import com.twq.TreeNode;

import java.util.ArrayDeque;

/**
 * 验证二叉搜索树
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4
 *
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class _98_ValidateBinarySearchTree {
    // 递归
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        int val = node.val;
        if (min != null && val <= min)
            return false;
        if (max != null && val >= max)
            return false;

        return isValidBST(node.left, min, val)
                && isValidBST(node.right, val, max);
    }

    // DFS 迭代
    public boolean isValidBST1(TreeNode root) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        double inorderVal = -Double.MAX_VALUE;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            if (curr.val <= inorderVal) return false;
            inorderVal = curr.val;

            curr = curr.right;
        }
        return true;
    }
}
