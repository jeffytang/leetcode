package com.twq.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍 III
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 */
public class _337_HouseRobber_iii {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 普通递归
    public int rob(TreeNode root) {
        if (root == null)
            return 0;

        // 抢劫一个爷爷和四个孙子的总价值
        int money1 = root.val;
        if (root.left != null) {
            money1 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            money1 += rob(root.right.left) + rob(root.right.right);
        }

        // 抢劫两个儿子的总价值
        int money2 = rob(root.left) + rob(root.right);

        // 拿到最大的值
        return Math.max(money1, money2);
    }

    // 普通递归 + 记忆化搜索
    private Map<TreeNode, Integer> memo = new HashMap<>();
    public int rob1(TreeNode root) {
        if (root == null)
            return 0;

        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        // 抢劫一个爷爷和四个孙子的总价值
        int money1 = root.val;
        if (root.left != null) {
            money1 += rob1(root.left.left) + rob1(root.left.right);
        }
        if (root.right != null) {
            money1 += rob1(root.right.left) + rob1(root.right.right);
        }

        // 抢劫两个儿子的总价值
        int money2 = rob1(root.left) + rob1(root.right);

        // 拿到最大的值
        int maxValue = Math.max(money1, money2);

        memo.put(root, maxValue);

        return  maxValue;
    }

    // 每个节点定义两个状态：偷与不偷
    // 如果不偷的话，那么得到的最大利益是去偷孩子节点得到的利益
    // 如果偷的话，那么得到的最大利益就是等于偷当前节点以及孙子节点之和
    public int rob2(TreeNode root) {
        int[] res = robIntern(root);
        return Math.max(res[0], res[1]);
    }

    public int[] robIntern(TreeNode root) {
        if (root == null)
            return new int[2];

        int[] res = new int[2];

        int[] left = robIntern(root.left);
        int[] right = robIntern(root.right);

        // 当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 当前节点选择偷：当前节点能偷到的最大钱数
        // = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
        res[1] = left[0] + right[0] + root.val;

        return res;
    }

}
