package com.twq.bit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 数组中两个数的最大异或值
 *
 * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 2^31 。
 *
 * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
 *
 * 你能在O(n)的时间解决这个问题吗？
 *
 * 示例:
 *
 * 输入: [3, 10, 5, 25, 2, 8]
 *
 * 输出: 28
 *
 * 解释: 最大的结果是 5 ^ 25 = 28.
 *
 * 链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
 */
public class _421_MaximumXOROfTwoNumbersInAnArray {
    /*
        基础知识：
            1. 0 和任意比特 x 异或结果还是 x 本身
            2. 如果 a, b 两个值相同，异或结果为 0

        使用哈希集来存储位前缀
     */
    public int findMaximumXOR(int[] nums) {
        // 首先计算数组中最大数的二进制长度 L。
        int maxNum = nums[0];
        for (int num : nums) maxNum = Math.max(maxNum, num);
        int L = Integer.toBinaryString(maxNum).length();

        int maxXor = 0, currXor;
        Set<Integer> prefixs = new HashSet<Integer>();
        // 从 i = L - 1遍历到 i = 0（代表着从最左侧的比特位 L - 1 遍历到最右侧的比特位 0）：
        for (int i = L - 1; i >= 0; i--) {
            // 将 max_xor 左移，释放出下一比特位的位置。
            maxXor <<= 1;
            // 将 currXor 最右侧的比特置为 1
            currXor = maxXor | 1;

            prefixs.clear();

            // 遍历 nums，计算出长度为 L - i 的所有可能的按位前缀。
            for (int num : nums) {
                // 将长度为 L - i 的按位前缀加入哈希集合 prefixes，按位前缀的计算公式如下：num >> i
                prefixs.add(num >> i);
            }

            // 遍历所有可能的按位前缀，检查是否存在 p1，p2 使得 p1^p2 == curr_xor。
            // 比较简单的做法是检查每个 p，看 curr_xor^p 是否存在。
            // 如果存在，就将 max_xor 改为 curr_xor（即将 max_xor 最右侧的比特位改为 1）。
            // 如果不存在，max_xor 最右侧的比特位继续保持为 0。
            for (int p : prefixs) {
                if (prefixs.contains(currXor ^ p)) {
                    maxXor = currXor;
                    break;
                }
            }
        }

        return maxXor;
    }

    // 使用 Trie 来存储位前缀
    /*
        算法结构如下所示：
            1. 将数字的每个 bit 插入到 Tire 中
            2. 找到插入 bit 在 Tire 中能得到的最大异或值
        算法的具体实现如下所示：
            1. 将所有数字转化成二进制形式。
            2. 将数字的二进制形式加入字典树，
            同时计算该数字在字典树中所能得到的最大异或值。
            再用该数字的最大异或值尝试性更新 max_xor。

       参考：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/solution/shu-zu-zhong-liang-ge-shu-de-zui-da-yi-huo-zhi-by-/
     */
    public int findMaximumXOR1(int[] nums) {
        class Node {
            Map<Character, Node> children = new HashMap<>();
        }

        // 首先计算数组中最大数的二进制长度 L。
        int maxNum = nums[0];
        for (int num : nums) maxNum = Math.max(maxNum, num);
        int L = Integer.toBinaryString(maxNum).length();

        // 将不满 L 个二进制位左边补 0
        int n = nums.length;
        int bitmask = 1 << L;
        String[] strNums = new String[n];
        for (int i = 0; i < n; i++) {
            strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
        }

        Node root = new Node();
        int maxXor = 0;
        // 遍历每一个数字
        for (String num : strNums) {
            Node curNode = root;
            Node xorNode = root;
            int curXor = 0;
            // 遍历当前数字的每一位
            for (Character bit : num.toCharArray()) {
                if (curNode.children.containsKey(bit)) {
                    curNode = curNode.children.get(bit);
                } else {
                    Node newNode = new Node();
                    curNode.children.put(bit, newNode);
                    curNode = newNode;
                }

                Character toggleBit = bit == '1' ? '0' : '1';
                if (xorNode.children.containsKey(toggleBit)) {
                    curXor = (curXor << 1) | 1; // 找到了异或值等于 1 ，所以当前的异或值加 1
                    xorNode = xorNode.children.get(toggleBit);
                } else {
                    curXor = curXor << 1; // 当前异或值等于 0， 所以当前的异或值加 0
                    xorNode = xorNode.children.get(bit);
                }
            }
            maxXor = Math.max(maxXor, curXor);
        }

        return maxXor;
    }
}
