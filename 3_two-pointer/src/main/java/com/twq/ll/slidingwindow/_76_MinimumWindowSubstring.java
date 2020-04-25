package com.twq.ll.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 *  给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 *  输入: S = "ADOBECODEBANC", T = "ABC"
 *  输出: "BANC"
 *
 *  说明：
 *      1. 如果 S 中不存这样的子串，则返回空字符串 ""
 *      2. 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class _76_MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0
                || t == null || t.length() == 0
                || s.length() < t.length())
            return "";

        // 统计字符串 t 中每个字符出现的次数
        Map<Character, Integer> needs = new HashMap<>();
        for (char c : t.toCharArray())
            needs.put(c, needs.getOrDefault(c, 0) + 1);

        // 用于记录滑动窗口中字符串 s 中每个字符串出现的次数
        Map<Character, Integer> window = new HashMap<>();
        // 用于记录滑动窗口已经匹配了字符串 t 中字符数
        int matched = 0;
        int start = 0; // 记录最小子串的起始位置
        int minLen = Integer.MAX_VALUE; // 记录最小子串的长度

        int left = 0, right = 0;
        while (right < s.length()) {
            // 1. 维护滑动窗口的右边
            // 1.1 将右边的字符加入到滑动窗口
            char cr = s.charAt(right);
            window.put(cr, window.getOrDefault(cr, 0) + 1);
            // 1.2 处理判断右边字符
            if (needs.containsKey(cr) // 右边字符属于 t 字符串
                    && window.get(cr) <= needs.get(cr)) // 该字符在窗口中出现的次数小于等于 t 中出现的次数
                matched++; // 匹配一个字符
            // 1.3 右指针右移一位
            right++;

            // 2. 维护滑动窗口的左边
            while (matched == t.length()) { // 滑动窗口中已经包含了 t 中所有的字符，且出现次数也是一样的
                // 2.1 更新结果
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                // 2.2 从滑动窗口中剔除左边字符
                char cl = s.charAt(left);
                if (needs.containsKey(cl) // 该字符属于 t 字符串
                        && window.get(cl) <= needs.get(cl)) // 该字符在窗口中出现的次数小于等于 t 中出现的次数
                    matched--;
                // 2.3 该字符在窗口中出现的次数减一
                window.put(cl, window.get(cl) - 1);
                // 2.4 左指针左移一位
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        _76_MinimumWindowSubstring s = new _76_MinimumWindowSubstring();
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC"));
    }
}
