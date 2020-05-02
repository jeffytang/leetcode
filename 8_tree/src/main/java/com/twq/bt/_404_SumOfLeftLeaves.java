package com.twq.bt;

import com.twq.TreeNode;

import java.util.ArrayDeque;

/**
 * 左叶子之和
 *
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 * 链接：https://leetcode-cn.com/problems/sum-of-left-leaves/
 */
public class _404_SumOfLeftLeaves {
    // 递归
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        int ans = 0;
        // 对于每个指定的节点，我们判断它的左节点是不是叶子节点
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null)
                // 如果左节点是叶子节点，将左节点的值累加到结果中
                ans += root.left.val;
            else
                // 否则，对左节点递归调用方法
                ans += sumOfLeftLeaves(root.left);
        }
        // 对于右节点，只有当右节点不是叶子节点的时候，我们递归调用方法
        if (root.right != null) {
            if (root.right.left != null || root.right.right != null)
                ans += sumOfLeftLeaves(root.right);
        }

        return ans;
    }

    // DFS
    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null)
            return 0;
        int ans = 0;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 对于每个指定的节点，我们判断它的左节点是不是叶子节点
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null)
                    // 如果左节点是叶子节点，将左节点的值累加到结果中
                    ans += node.left.val;
                else
                    // 否则将左节点推入到栈中，后续处理
                    stack.push(node.left);
            }

            if (node.right != null) {
                // 对于右节点，只有当右节点不是叶子节点的时候，我们将其推入栈中，后续处理
                if (node.right.left != null || node.right.right != null)
                    stack.push(node.right);
            }
        }

        return ans;
    }

}
