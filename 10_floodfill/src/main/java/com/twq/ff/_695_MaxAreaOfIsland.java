package com.twq.ff;

/**
 * 岛屿的最大面积
 *
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 *
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，
 * 这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 */
public class _695_MaxAreaOfIsland {
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    private int m;
    private int n;

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        m = grid.length;
        if (m == 0)
            return res;
        n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, area(grid, i, j, visited));
            }
        }

        return res;
    }

    private int area(int[][] grid, int row, int col, boolean[][] visited) {
        if (!inArea(row, col)
                || visited[row][col]
                || grid[row][col] == 0)
            return 0;

        int ans = 0;
        visited[row][col] = true;
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            ans += area(grid, newRow, newCol, visited);
        }

        return 1 + ans;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }
}
