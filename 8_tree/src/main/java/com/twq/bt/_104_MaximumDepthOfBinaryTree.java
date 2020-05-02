package com.twq.bt;


import com.twq.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class _104_MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        int dept = recursion(root);
        return dept;
    }

    private int bfs(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            depth++;
        }
        return depth;
    }

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;

        int depth = 0;

        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(root, 1));
        while (!stack.isEmpty()) {
            Node currNode = stack.pop();
            TreeNode node = currNode.node;
            int currDept = currNode.depth;
            depth = Math.max(depth, currDept);

            if (node.left != null) stack.push(new Node(node.left, currDept + 1));
            if (node.right != null) stack.push(new Node(node.right, currDept + 1));
        }

        return depth;
    }

    private class Node {
        TreeNode node;
        int depth;

        Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    private int recursion(TreeNode root) {
        if (root == null)
            return 0;
        // 拿到左右子树中最高的树的高度
        int leftMaxDepth = recursion(root.left);
        int rightMaxDepth = recursion(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
