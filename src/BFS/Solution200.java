package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @作者: one者天下
 * @时间: 2021/5/12 22:39
 * @描述: 岛屿数量
 */
public class Solution200 {
//    private int count = 0;
//
//    private final static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
// 深度优先遍历
//    public int numIslands(char[][] grid) {
//        if (grid.length == 1 && grid[0].length == 1 ){
//            if (grid[0][0] == '1') return 1;
//            else return 0;
//        }
//        int row = grid.length;
//        int col = grid[0].length;
//        for (int i = 0; i < row; i++) {
//            if (grid[i][0] == '1') {
//                dfs(i,0,grid,row,col);
//                count++;
//            }
//            if (grid[i][col-1] == '1') {
//                dfs(i,col-1,grid,row,col);
//                count++;
//            }
//        }
//        for (int i = 0; i < col; i++) {
//            if (grid[0][i] == '1'){
//                dfs(0,i,grid,row,col);
//                count++;
//            }
//            if (grid[row-1][i] == '1'){
//                dfs(row-1,i,grid,row,col);
//                count++;
//            }
//        }
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                if (grid[i][j] == '1'){
//                    dfs(i,j,grid,row,col);
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
    // 解法一，深度优先遍历
    private void dfs(int i,int j,char[][] grid,int row,int col){
        grid[i][j] = '#';
        for (int[] dir : dirs) {
            int tem_i = i + dir[0];
            int tem_j = j + dir[1];
            if (tem_i < 0 || tem_i >= row || tem_j < 0 || tem_j >= col || grid[tem_i][tem_j] != '1') continue;
            dfs(tem_i,tem_j,grid,row,col);
        }
    }
    private int count = 0;
    private final static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    // 创建一个记录坐标的节点类，以便实现广度优先遍历
    private static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 解法二，广度优先遍历
    private void bfs(int i,int j,char[][] grid,int row,int col){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i,j));
        while (!queue.isEmpty()){
            Point point = queue.poll();
            if (point.x >= 0 && point.x < row && point.y >= 0 && point.y < col && grid[point.x][point.y] == '1'){
                grid[point.x][point.y] = '#';
                for (int[] dir : dirs) {
                    int tem_i = point.x + dir[0];
                    int tem_j = point.y + dir[1];
                    queue.add(new Point(tem_i,tem_j));
                }
            }
        }
    }
    public int numIslands(char[][] grid) {
        if (grid.length == 1 && grid[0].length == 1){
            if (grid[0][0] == '1') return 1;
            else return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1'){
                    bfs(i,j,grid,row,col);
                    count++;
                }
            }
        }
        return count;
    }
}
