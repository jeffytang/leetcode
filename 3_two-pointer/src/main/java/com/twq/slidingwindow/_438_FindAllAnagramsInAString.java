package com.twq.slidingwindow;

import java.util.ArrayList;
import java.util.List;

/**
 *  给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 *  字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * 请先参考 76 号题
 *
 */
public class _438_FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0
                || p == null || p.length() == 0
                || s.length() < p.length())
            return res;

        int[] needs = new int[26];
        for (char c : p.toCharArray())
            needs[c - 'a']++;

        int[] window = new int[26];
        int matched = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char cr = s.charAt(right);
            int rIdx = cr - 'a';
            window[rIdx]++;
            if (needs[rIdx] != 0
                    && needs[rIdx] >= window[rIdx])
                matched++;
            right++;

            while (matched == p.length()) {
                // 如果窗口的长度等于字符串 p 的长度，则将 left 索引加入结果集
                if (right - left == p.length())
                    res.add(left);

                char cl = s.charAt(left);
                int lIdx = cl - 'a';
                if (needs[lIdx] != 0
                        && needs[lIdx] >= window[lIdx])
                    matched--;

                window[lIdx]--;
                left++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        _438_FindAllAnagramsInAString a = new _438_FindAllAnagramsInAString();
        System.out.println(a.findAnagrams("cbaebabacd", "abc"));
    }
}
