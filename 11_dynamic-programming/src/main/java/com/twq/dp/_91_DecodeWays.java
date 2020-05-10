package com.twq.dp;

import java.util.Arrays;

/**
 * 解码方法
 *
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * 链接：https://leetcode-cn.com/problems/decode-ways
 */
public class _91_DecodeWays {
    // 普通递归
    public int numDecodings1(String s) {
        return helper(s, 0);
    }

    // 计算从字符串中 start 位置开始到字符串结束一共有多少种解码方式
    private int helper(String s, int start) {
        if (start == s.length()) {
            return 1;
        }

        if (s.charAt(start) == '0') {
            return 0;
        }

        int ans1 = helper(s, start + 1);
        int ans2 = 0;
        if (start < s.length() - 1) {
            int one = s.charAt(start + 1) - '0';
            int ten = (s.charAt(start) - '0') * 10;
            // 如果 start 对应的字符和 start + 1 对应的字符数字之和小于等于 26 的话
            if (one + ten <= 26) {
                // 那么从 start 到结尾解码的个数就等于 start + 1 开始到结尾解码的个数
                // 和 start + 2 开始到结尾解码个数之和
                ans2 = helper(s, start + 2);
            }
            // 否则，从 start 到结尾解码的个数就等于 start + 1 开始到结尾解码的个数
        }

        return ans1 + ans2;
    }

    // 普通递归 + 记忆化搜索
    public int numDecodings2(String s) {
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        return helper(s, 0, memo);
    }

    private int helper(String s, int start, int[] memo) {
        if (start == s.length()) {
            return 1;
        }

        if (s.charAt(start) == '0') {
            return 0;
        }

        if (memo[start] != -1) {
            return memo[start];
        }

        int ans1 = helper(s, start + 1, memo);
        int ans2 = 0;
        if (start < s.length() - 1) {
            int one = s.charAt(start + 1) - '0';
            int ten = (s.charAt(start) - '0') * 10;
            if (one + ten <= 26) {
                ans2 = helper(s, start + 2, memo);
            }
        }

        memo[start] = ans1 + ans2;

        return ans1 + ans2;
    }

    // 动态规划
    public int numDecodings3(String s) {
        int len = s.length();
        // 状态定义
        // dp[i] 表示字符串 s 从 i 到字符串结尾的解法方式的个数
        int[] dp = new int[s.length() + 1];

        // 状态初始化
        dp[len] = 1;
        if (s.charAt(len - 1) != '0') {
            dp[len - 1] = 1;
        } else {
            dp[len - 1] = 0;
        }

        // 状态转移
        for (int i = len - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }

            int one = s.charAt(i + 1) - '0';
            int ten = (s.charAt(i) - '0') * 10;
            if (one + ten <= 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }

        // 返回结果
        return dp[0];
    }
}
