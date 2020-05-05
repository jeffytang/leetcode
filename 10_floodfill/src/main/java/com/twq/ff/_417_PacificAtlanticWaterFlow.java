package com.twq.ff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 太平洋大西洋水流问题
 *
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。
 * “太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 *
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 *
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 *
 *  
 *
 * 提示：
 *
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 *  
 *
 * 示例：
 * 给定下面的 5x5 矩阵:
 *
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 *
 * 返回:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 *
 * 链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
 */
public class _417_PacificAtlanticWaterFlow {
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    private int m;
    private int n;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();

        m = matrix.length;
        if (m == 0)
            return res;
        n = matrix[0].length;

        int[][] pacificReachFlags = new int[m][n];
        int[][] atlanticReachFlags = new int[m][n];

        for (int i = 0; i < n; i++) {
            // 计算能到达第一行的所有元素，因为第一行所有元素都能到达太平洋
            dfs(matrix, 0, i, pacificReachFlags);
            // 计算能到达最后一行的所有元素，因为最后一行所有元素都能到达大西洋
            dfs(matrix, m - 1, i, atlanticReachFlags);
        }

        for (int i = 0; i < m; i++) {
            // 计算能到达第一列的所有元素，因为第一列所有元素都能到达太平洋
            dfs(matrix, i, 0, pacificReachFlags);
            // 计算能到达最后一列的所有元素，因为最后一列所有元素都能到达大西洋
            dfs(matrix, i, n - 1, atlanticReachFlags);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 得到能到达大平洋和大西洋的元素
                if (pacificReachFlags[i][j] == 1
                        && atlanticReachFlags[i][j] == 1) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int[][] matrix, int row, int col, int[][] reachFlags) {
        // 使用 1 来表示当前元素可以到达太平洋或者大西洋
        reachFlags[row][col] = 1;
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (inArea(newRow, newCol)
                    && reachFlags[newRow][newCol] != 1
                    && matrix[row][col] <= matrix[newRow][newCol]) {
                dfs(matrix, newRow, newCol, reachFlags);
            }
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }
}
