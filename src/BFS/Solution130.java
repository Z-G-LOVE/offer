package BFS;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @作者: one者天下
 * @时间: 2021/5/11 20:51
 * @描述: 被围绕的区域
 */
public class Solution130 {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        int row = board.length;
        int col = board[0].length;
        for (int j = 0;j < col;j++){
            if (board[0][j] == 'O') bfs(0,j,row,col,board);
            if (board[row-1][j] == 'O') bfs(row-1,j,row,col,board);
        }
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') bfs(i,0,row,col,board);
            if (board[i][col-1] == 'O') bfs(i,col-1,row,col,board);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == 'B') board[i][j] = 'O';
            }
        }
    }
    // 记录递归的方向和步长
    private final int[][] dirs = {{1,0},{-1,0},{0,-1},{0,1}};
    // dfs的解法
    private void dfs(int i, int j, char[][] board,int row,int col){
        board[i][j] = 'B';
        for (int[] dir : dirs) {
            int temp_i = i + dir[0];
            int temp_j = j + dir[1];
            if (temp_i < 0 || temp_i >= row || temp_j < 0 || temp_j >= col || board[i][j] != 'O') continue;
            dfs(temp_i,temp_j,board,row,col);
        }
    }
    // bfs的解法
    // 存放节点的坐标
    private static class point{
        int x;
        int y;

        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 实现bfs
    private void bfs(int i,int j,int row,int col,char[][] board){
        LinkedList<point> queue = new LinkedList<>();
        queue.add(new point(i,j));
        while (!queue.isEmpty()){
            point poll = queue.poll();
            if (poll.x>=0 && poll.x < row && poll.y>=0 && poll.y<col && board[poll.x][poll.y] == 'O')
            {
                board[poll.x][poll.y] = 'B';
                for (int[] dir : dirs) {
                    queue.add(new point(poll.x+dir[0], poll.y+dir[1]));
                }
            }

        }
    }
    private void bfs(int i, int j, char[][] board, int row, int col) {
        Deque<Point> queue = new LinkedList<>();
        queue.offer(new Point(i, j));
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            if (tmp.x >= 0 && tmp.x < row && tmp.y >= 0 && tmp.y < col && board[tmp.x][tmp.y] == 'O') {
                board[tmp.x][tmp.y] = 'B';
                for (int[] dir : dirs) queue.offer(new Point(tmp.x + dir[0], tmp.y + dir[1]));
            }
        }
    }


}
