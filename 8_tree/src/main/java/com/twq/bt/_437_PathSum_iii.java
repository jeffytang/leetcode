package com.twq.bt;

import com.twq.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 路径总和 III
 *
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 *
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 */
public class _437_PathSum_iii {
    // DFS 递归
    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;

        int res = findPath(root, sum);

        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);

        return res;
    }

    private int findPath(TreeNode node, int sum) {
        if (node == null) return 0;

        int res = 0;
        if (node.val == sum) {
            res += 1;
        }

        res += findPath(node.left, sum - node.val);
        res += findPath(node.right, sum - node.val);

        return res;
    }

    // 前缀树
    // 和 two sum 的思想差不多
    public int pathSum1(TreeNode root, int sum) {
        // key : the prefix sum, value : how many ways get to this prefix sum
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        return helper(root, 0, sum, prefixSumMap);
    }

    private int helper(TreeNode node,
                        int currSum,
                        int target,
                        Map<Integer, Integer> prefixSumMap) {
        if (node == null) return 0;

        currSum += node.val;

        // whenever reach a node, we check if prefix sum - target exists in hashmap or not
        // if it does, we added up the ways of prefix sum - target into res
        int res = prefixSumMap.getOrDefault(currSum - target, 0);

        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        res += helper(node.left, currSum, target, prefixSumMap);
        res += helper(node.right, currSum, target, prefixSumMap);

        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);

        return res;
    }
}
