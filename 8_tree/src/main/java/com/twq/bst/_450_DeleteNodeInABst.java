package com.twq.bst;

import com.twq.TreeNode;

/**
 * 删除二叉搜索树中的节点
 *
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
 * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 *
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 *
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 *
 * 参考：https://coding.imooc.com/lesson/207.html#mid=13477
 */
public class _450_DeleteNodeInABst {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            // 在 left 树中删除元素 e
            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.val) {
            // 在 right 树中删除元素 e
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            // 如果左子树为空，则直接删除这个节点，返回当前节点的右子树
            if (root.left == null) {
                TreeNode rightNode = root.right;
                root.right = null;
                return rightNode;
            }
            // 如果右子树为空，则直接删除这个节点，返回当前节点的左子树
            if (root.right == null) {
                TreeNode leftNode = root.left;
                root.left = null;
                return leftNode;
            }

            // 当前节点 node 有左右子树
            // 找到节点的后继节点
            TreeNode successor = minNode(root.right);
            successor.right = removeMin(root.right);
            successor.left = root.left;

            root.left = root.right = null;
            return successor;
        }
    }

    private TreeNode minNode(TreeNode node) {
        if (node == null)
            return null;
        if (node.left == null)
            return node;
        return minNode(node.left);
    }

    // 删除以 node 为根的树的最小值
    // 并返回新的根节点
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            TreeNode rightNode = node.right;
            node.right = null;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }
}
