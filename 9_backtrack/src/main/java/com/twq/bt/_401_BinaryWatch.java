package com.twq.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表
 *
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 *
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 *
 * 例如，上面的二进制手表读取 “3:25”。
 *
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 *
 * 案例:
 *
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *  
 *
 * 注意事项:
 *
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 *
 * 链接：https://leetcode-cn.com/problems/binary-watch
 *
 */
public class _401_BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();

        int[] nums1 = {8, 4, 2, 1};
        int[] nums2 = {32, 16, 8, 4, 2, 1};

        for (int i = 0; i <= num; i++) {
            // 拿到 i 个小时的组合
            List<Integer> hours = generateDigit(nums1, i);
            // 拿到 num - i 个分钟的组合
            List<Integer> minutes = generateDigit(nums2, num - i);
            for (int hour : hours) {
                if (hour >= 12) continue;
                for (int minute : minutes) {
                    if (minute >= 60) continue;
                    // 组合小时和分钟
                    res.add(hour + ":" + (minute < 10 ? "0" + minute : minute));
                }
            }
        }

        return res;
    }

    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        // 回溯从数组 nums 中拿到指定个数 count 的和组合
        generateDigitHelper(nums, 0, count, 0, res);
        return res;
    }

    private void generateDigitHelper(int[] nums,
                                     int start,
                                     int count,
                                     int sum,
                                     List<Integer> res) {
        if (count == 0) {
            res.add(sum);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            generateDigitHelper(nums, i + 1, count - 1, sum + nums[i], res);
        }
    }
}
