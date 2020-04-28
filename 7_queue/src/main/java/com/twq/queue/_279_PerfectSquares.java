package com.twq.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）
 * 使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * 链接：https://leetcode-cn.com/problems/perfect-squares/
 */
public class _279_PerfectSquares {
    /*
        对问题建模：
            这个问题可以转换为图论问题
            从 n 到 0，每个数字表示一个节点。
            如果两个数字 x 到 y 相差一个完全平方数，则连接一条边
            而且每条边都是从大的数字指向小的数字
            这样我们就得到一个有向无权图
            那么原问题转化为：求这个有向无权图中从 n 到 0 的最短路径
     */
    public int numSquares(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n can not be negative");
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, 0}); // 数组中的第一个元素是数字，第二个元素表示到达这个数字的步数

        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while (!queue.isEmpty()) {
            int num = queue.peek()[0];
            int step = queue.peek()[1];
            queue.poll();

            // 访问当前点的临边对应的每个点 (即是相差一个完全平方数的点)
            for (int i = 0; ; i++) {
                int nextNum = num - i * i;

                if (nextNum < 0)
                    break;

                if (nextNum == 0)
                    return step + 1;

                if (!visited[nextNum]) {
                    queue.add(new int[] {nextNum, step + 1});
                    visited[nextNum] = true;
                }
            }
        }
        throw new RuntimeException("No Solution");
    }
}
