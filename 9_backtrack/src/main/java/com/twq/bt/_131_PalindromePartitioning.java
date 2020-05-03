package com.twq.bt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 分割回文串
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 */
public class _131_PalindromePartitioning {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.isEmpty()) {
            return res;
        }

        ArrayDeque<String> deque = new ArrayDeque<>();
        findPalindrome(s, 0, deque);
        return res;
    }

    /**
     *
     * @param s     原始字符串
     * @param start 起始字符的索引
     * @param path  记录从根节点到叶子节点的路径
     */
    private void findPalindrome(String s, int start, Deque<String> path) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (!checkPalindrome(s, start, i))
                continue;

            path.addLast(s.substring(start, i + 1));
            findPalindrome(s, i + 1, path);
            // 回溯
            path.removeLast();
        }
    }

    /**
     * 这一步的时间复杂度是 O(N)，因此，可以采用动态规划先把回文子串的结果记录在一个表格里
     *
     * @param str
     * @param left  子串的左边界，可以取到
     * @param right 子串的右边界，可以取到
     * @return
     */
    private boolean checkPalindrome(String str, int left, int right) {
        // 严格小于即可
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
