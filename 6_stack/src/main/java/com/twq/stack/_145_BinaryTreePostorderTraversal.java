package com.twq.stack;

import java.util.*;

/**
 * 题目：二叉树的后序遍历
 *
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class _145_BinaryTreePostorderTraversal {
    // 递归
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    private void postOrder(TreeNode node, List<Integer> res) {
        if (node == null) return;

        postOrder(node.left, res);
        postOrder(node.right, res);
        res.add(node.val);
    }

    // 迭代 (前序遍历的反向)
    public List<Integer> postorderTraversal1(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 先访问的越靠后
            // 1. 先访问根节点
            res.addFirst(node.val);
            // 3. 最后访问左节点
            if (node.left != null) stack.push(node.left);
            // 2. 再访问右节点
            if (node.right != null) stack.push(node.right);
        }
        return res;
    }

    // 迭代 (严格的后序遍历)
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        Set<TreeNode> visited = new HashSet<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            boolean isLeftVisited = true;
            boolean isRightVisited = true;

            // 如果右节点没有被访问，将右节点放入到栈中
            if (node.right != null && !visited.contains(node.right)) {
                isRightVisited = false;
                stack.push(node.right);
            }

            // 如果左节点没有被访问，将左节点放入到栈中
            if (node.left != null && !visited.contains(node.left)) {
                isLeftVisited = false;
                stack.push(node.left);
            }

            // 如果左右节点都已经访问了，再访问根节点
            if (isLeftVisited && isRightVisited) {
                res.add(node.val);
                visited.add(node);
                stack.pop();
            }
        }

        return res;
    }
}
