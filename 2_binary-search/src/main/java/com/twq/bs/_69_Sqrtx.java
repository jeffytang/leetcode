package com.twq.bs;

public class _69_Sqrtx {

    public int mySqrt(int x) {
        // 每个正整数的平方根都会小于 这个整数的一半加 1
        // 当然，这里的 r 也可以初始化 x，但是没有 x / 2 + 1 高效
        int l = 1, r = x / 2 + 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // int square = mid * mid; // 使用这种方式会溢出
            if (mid == x / mid)
                return mid;
            else if (mid < x / mid)
                l = mid + 1;
            else
                r = mid - 1;
        }

        return r;
    }
}
