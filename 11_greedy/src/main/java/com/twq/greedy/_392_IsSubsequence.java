package com.twq.greedy;

/**
 * 判断子序列
 *
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），
 * 而 s 是个短字符串（长度 <=100）。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 *
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 *
 * 返回 false.
 *
 * 后续挑战 :
 *
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
 * 在这种情况下，你会怎样改变代码？
 *
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 */
public class _392_IsSubsequence {
    /*
        贪心算法必须具备后无效性，也就是不必考虑前面的影响，只需考虑当前的状态。

        示例 1:
            s = "abc", t = "ahbgdc"

            返回 true.
        看上面的例子，
        要使字符串s的排序要和字符串t的排序一致，我们只需考虑两个要素:
            1. 当字符 'a' 出现，判断字符传 t 中是否存在字符 'a'
            2. t 中字符 'a' 之后的剩余字符串是否存在 'b'

        用一句通俗的话就是剩余字符串中是否存在下一个字符；利用贪心算法的概念就是局部是否存在最优解。
     */
    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index + 1);
            if (index == -1) return false;
        }
        return true;
    }

    /*
        如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
        在这种情况下，你会怎样改变代码？

        这时候处理每一个子串都需要扫描一遍 T 是很费时的。

        在这种情况下，我们需要在匹配前对 T 做预处理，利用一个二维数组记录每个位置上 26 个字符下一次出现的位置，
        这里的字符是'a' ~ 'z'，所以这个数组的大小是 dp[n][26]，n 为 T 的长度。
        那么每处理一个子串只需要扫描一遍 Si 即可，因为在数组的帮助下我们对 T 是“跳跃”扫描的。
     */
    public boolean isSubsequence_1(String s, String t) {
        // 1. 对字符串 t 进行预处理
        t = " " + t;
        int len = t.length();
        // 记录每个位置的下一个ch的位置
        int[][] dp = new int[len][26];
        for (int i = 0; i < 26; i++) {
            int nextPos = -1;
            for (int j = len - 1; j >= 0; j--) {
                dp[i][j] = nextPos;
                if (t.charAt(j) == i + 'a') {
                    nextPos = j;
                }
            }
        }

        // 2. 匹配
        int i = 0;
        for (char c : s.toCharArray()) {
            i = dp[i][c - 'a'];
            if (i == -1) return false;
        }
        return true;
    }
}
