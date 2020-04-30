package com.twq.bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 */
public class _111_MinimumDepthOfBinaryTree {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) {
            return 1;
        }

        int minDepth = Integer.MAX_VALUE;
        if (root.left != null)
            minDepth = Math.min(minDepth(root.left), minDepth);
        if (root.right != null)
            minDepth = Math.min(minDepth(root.right), minDepth);

        return minDepth + 1;
    }

    private int bfs(TreeNode root) {
        if (root == null)
            return 0;

        int depth = 0;
        // 判断是否已经到了叶子节点
        boolean isLeaveNode = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty() && !isLeaveNode) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left == null && node.right == null) {
                    isLeaveNode = true;
                    break;
                }

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return depth;
    }
}
