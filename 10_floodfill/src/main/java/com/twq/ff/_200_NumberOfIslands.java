package com.twq.ff;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 */
public class _200_NumberOfIslands {
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int m;
    private int n;

    public int numIslands(char[][] grid) {
        m = grid.length;
        if (m == 0)
            return 0;
        n = grid[0].length;
        return dfs(grid);
    }

    // 深度优先搜素
    private int dfs(char[][] grid) {
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    searchOne(grid, i, j);
                }
            }
        }
        return res;
    }

    // 从 grid[row][col] 开始进行 floodfill
    private void searchOne(char[][] grid, int row, int col) {
        // 将 1 设置为 0，表示这个 1 已经访问过
        grid[row][col] = '0';
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (inArea(newRow, newCol) && grid[newRow][newCol] == '1') {
                searchOne(grid, newRow, newCol);
            }
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }

    // 广度优先搜素
    private int bfs(char[][] grid) {
        int res = 0;
        int m = grid.length;
        if (m == 0) return res;
        int n = grid[0].length;

        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res += 1;
                    grid[i][j] = '0';

                    Queue<int[]> neighbors = new LinkedList<>();
                    neighbors.add(new int[] {i, j});
                    while (!neighbors.isEmpty()) {
                        int[] curr = neighbors.poll();
                        int currRow = curr[0];
                        int currCol = curr[1];
                        for (int[] dir : dirs) {
                            int row = currRow + dir[0];
                            int col = currCol + dir[1];
                            if (row >= 0 && row < m && col >= 0 && col < n
                                    && grid[row][col] == '1') {
                                neighbors.add(new int[]{row, col});
                                grid[row][col] = '0';
                            }
                        }
                    }
                }
            }
        }

        return res;
    }
}
