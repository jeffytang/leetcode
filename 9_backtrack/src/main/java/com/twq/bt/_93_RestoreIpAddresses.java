package com.twq.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原IP地址
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 */
public class _93_RestoreIpAddresses {
    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.isEmpty())
            return res;

        restoreIp(s, 0, "", 0);

        return res;
    }

    /**
     * 回溯恢复 ip
     * @param s         需要恢复 ip 的原始字符串
     * @param index     处理字符串 s 中的第几个字符
     * @param restored  已经恢复了的 ip 部分
     * @param count     已经恢复了的 ip 的段数
     */
    private void restoreIp(String s, int index, String restored, int count) {
        if (count > 4) return;
        if (count == 4 && index == s.length()) {
            res.add(restored);
            return;
        }

        for (int i = 1; i < 4; i++) {
            if (index + i > s.length()) break;
            String segment = s.substring(index, index + i);
            if (!isValidIpSegment(segment)) continue;
            String suffix = count == 3 ? "" : ".";
            restoreIp(s, index + i, restored + segment + suffix, count + 1);
        }
    }

    private boolean isValidIpSegment(String segment) {
        // 长度不能大于 3
        int m = segment.length();
        if (m > 3) return false;

        // 1. ip 段如果是以 0 开始的话，那么这个 ip 段只能是 0
        // 2. ip 段需要小于等于 255
        return (segment.charAt(0) == '0') ? (m == 1) : (Integer.valueOf(segment) <= 255);
    }
}
