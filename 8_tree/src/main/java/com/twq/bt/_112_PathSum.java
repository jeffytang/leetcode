package com.twq.bt;

import java.util.ArrayDeque;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，
 * 这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 链接：https://leetcode-cn.com/problems/path-sum
 */
public class _112_PathSum {
    // 递归
    // 时间复杂度：我们访问每个节点一次，时间复杂度为 O(N) ，其中 N 是节点个数。
    // 空间复杂度：最坏情况下，整棵树是非平衡的，例如每个节点都只有一个孩子，
    //  递归会调用 N 次（树的高度），因此栈的空间开销是 O(N) 。
    //  但在最好情况下，树是完全平衡的，高度只有 log(N)，
    //  因此在这种情况下空间复杂度只有 O(log(N)) 。
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        sum -= root.val;
        //如果当前节点是叶子，检查 sum 值是否为 0，也就是是否找到了给定的目标和。
        if (root.left == null && root.right == null)
            return sum == 0;
        // 如果当前节点不是叶子，对它的所有孩子节点，递归调用 hasPathSum 函数，
        // 其中 sum 值减去当前节点的权值
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    public boolean dfs(TreeNode root, int sum) {
        if (root == null)
            return false;

        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(root, sum - root.val));
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            TreeNode currNode = node.node;
            int currSum = node.sum;

            if (currNode.right == null
                    && currNode.left == null
                    && currSum == 0) {
                return true;
            }

            if (currNode.left != null) {
                stack.push(new Node(currNode.left, currSum - currNode.left.val));
            }

            if (currNode.right != null) {
                stack.push(new Node(currNode.right, currSum - currNode.right.val));
            }
        }

        return false;
    }

    private class Node {
        TreeNode node;
        int sum;

        public Node(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }
}
