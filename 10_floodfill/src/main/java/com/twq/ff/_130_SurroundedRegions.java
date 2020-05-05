package com.twq.ff;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 被围绕的区域
 *
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 */
public class _130_SurroundedRegions {
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int m;
    private int n;

    public void solve(char[][] board) {
        m = board.length;
        if (m == 0)
            return;
        n = board[0].length;
        dfs(board);
    }
    // 深度优先
    private void dfs(char[][] board) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从在边缘上的 O 开始进行 floodfill
                boolean isEdge = i == 0 || i == m - 1 || j == 0 || j == n - 1;
                if (isEdge && board[i][j] == 'O') {
                    serachO(board, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                // 将 # 改回到 O
                if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    // floodfill
    private void serachO(char[][] board, int row, int col) {
        // 将 O 替换成 #
        board[row][col] = '#';
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (inArea(newRow, newCol)
                    && board[newRow][newCol] == 'O'
                    && board[newRow][newCol] != '#') {
                serachO(board, newRow, newCol);
            }
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }

    // 广度优先，比 dfs 慢
    public void bfs(char[][] board) {
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        Set<String> set = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        // 将边界中的字符 O 都放入到队列中
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                queue.add(new int[]{i, 0});
            }
            if (board[i][n - 1] == 'O') {
                queue.add(new int[]{i, n - 1});
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                queue.add(new int[]{0, i});
            }
            if (board[m - 1][i] == 'O') {
                queue.add(new int[]{m - 1, i});
            }
        }

        // 从边界中的字符 O 开始广度搜索，搜索到的字符 O 都不能变成字符 X
        while (!queue.isEmpty()) {
            int[] currEle = queue.poll();
            int currRow = currEle[0];
            int currCol = currEle[1];
            set.add(currRow + "$" + currCol);
            visited[currRow][currCol] = true;
            for (int[] dir : dirs) {
                int row = currRow + dir[0];
                int col = currCol + dir[1];
                if (inArea(row, col) && !visited[row][col] && board[row][col] == 'O') {
                    set.add(row + "$" + col);
                    queue.add(new int[]{row, col});
                }
            }
        }

        // 将需要变成字符 X 的字符 O 变成字符 X
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O' && !set.contains(i + "$" + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
