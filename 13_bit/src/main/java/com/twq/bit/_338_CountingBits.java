package com.twq.bit;

/**
 * 比特位计数
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，
 * 计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * 链接：https://leetcode-cn.com/problems/counting-bits
 */
public class _338_CountingBits {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = popCount(i);
        }
        return res;
    }

    private int popCount(int num) {
        int count = 0;
        for (; num != 0; count++) {
            num &= num - 1;
        }
        return count;
    }

    /*
        p(0) = 0
        p(1) = 1
        p(2) = p(0 + 2) = p(0) + 1 = 1
        p(3) = p(1 + 2) = p(1) + 1 = 2
        p(4) = p(0 + 4) = p(0) + 1 = 1
        p(5) = p(1 + 4) = p(1) + 1 = 2
        p(6) = p(2 + 4) = p(2) + 1 = 2
        p(7) = p(3 + 4) = p(3) + 1 = 3
        p(8) = p(0 + 8) = p(0) + 1 = 1
        p(9) = p(1 + 7) = p(1) + 1 = 2
        ....
        p(x + b) = p(x) + 1 ，其中 b = 2^m

        参考：https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode/
     */
    public int[] countBits1(int num) {
        int[] res = new int[num + 1];
        int i = 0, b = 1;
        while (b <= num) {
            while (i < b && i + b <= num) {
                res[i + b] = res[i] + 1;
                i++;
            }
            i = 0;
            b <<= 1;
        }
        return res;
    }
}
