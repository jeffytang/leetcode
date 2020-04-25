package com.twq.slidingwindow;

/**
 *  给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *  输入: "abcabcbb"
 *  输出: 3
 *  解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *  输入: "bbbbb"
 *  输出: 1
 *  解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 *  输入: "pwwkew"
 *  输出: 3
 *  解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *       请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 */
public class _3_LongestSubstringWithoutRepeatingCharacters {

    // 从左开始判断
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int ans = 0;
        int left = 0, right = -1;

        int[] freq = new int[256];
        char[] chars = s.toCharArray();

        while (left < s.length()) {

            // 维护右指针，如果 right 下一个元素没出现过，则将右边的元素纳入到当前窗口
            if (right + 1 < s.length() && freq[chars[right + 1]] == 0) {
                right++;
                freq[chars[right]]++;
            } else { // 维护左指针，如果左边的元素已经出现过，那么则从当前窗口剔除
                freq[chars[left]]--;
                left++;
            }

            // 更新结果
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
    // 从右开始判断
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;
        int ans = 0;
        int[] window = new int[256];
        int left = 0, right = 0;
        while (right < s.length()) {
            char cr = s.charAt(right);
            window[cr]++;
            right++;

            while (window[cr] > 1) {
                char cl = s.charAt(left);
                window[cl]--;
                left++;
            }

            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    public static void main(String[] args) {
        _3_LongestSubstringWithoutRepeatingCharacters ee = new _3_LongestSubstringWithoutRepeatingCharacters();
        System.out.println(ee.lengthOfLongestSubstring(" "));
    }
}
