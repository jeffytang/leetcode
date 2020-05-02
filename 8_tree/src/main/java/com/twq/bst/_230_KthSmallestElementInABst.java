package com.twq.bst;

import com.twq.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中第K小的元素
 *
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 * 我们可以结合一个双向链表，这个双向链表维护着二叉搜素树的中序遍历的顺序，那么：
 *  1. 往一棵二叉搜素树中插入元素的时候，维护该元素在双向链表中对应的位置，时间复杂度是 O(logn)
 *  2. 只需要在双向链表中查询第 k 个最小数，时间复杂度是 O(k)
 *
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 */
public class _230_KthSmallestElementInABst {
    // 中序遍历
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(k - 1);
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null)
            return;

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    // DFS 迭代
    public int kthSmallest1(TreeNode root, int k) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            k--;
            if (k == 0) return curr.val;
            curr = curr.right;
        }

        return -1;
    }
}
