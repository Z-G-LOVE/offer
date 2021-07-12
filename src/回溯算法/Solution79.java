package 回溯算法;

/**
 * @作者: one者天下
 * @时间: 2021/5/4 22:23
 * @描述: 单词搜索
 */
public class Solution79 {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] used = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == word.charAt(0)){
                    used[i][j] = true;
                    if (dfs(word,1,board,used,row,col,i,j)) return true;
                    used[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean dfs(String word , int index , char[][] board ,boolean[][] used , int row,int col,int i , int j){
        if (index == word.length()) return true;
        // 向上走
        if (i>=1 && i<row && j>=0 && j<col && !used[i-1][j] && board[i-1][j] == word.charAt(index)){
            used[i-1][j] = true;
            if (dfs(word,index+1,board,used,row,col,i-1,j)) return true;
            used[i-1][j] = false;
        }
        // 向下走
        if (i>=0 && i<row-1 && j>=0 && j<col && !used[i+1][j] && board[i+1][j] == word.charAt(index)){
            used[i+1][j] = true;
            if (dfs(word,index+1,board,used,row,col,i+1,j)) return true;
            used[i+1][j] = false;
        }
        // 向左走
        if (i>=0 && i<row && j>=1 && j<col && !used[i][j-1] && board[i][j-1] == word.charAt(index)){
            used[i][j-1] = true;
            if (dfs(word,index+1,board,used,row,col,i,j-1)) return true;
            used[i][j-1] = false;
        }
        // 向右走
        if (i>=0 && i<row && j>=0 && j<col-1 && !used[i][j+1] && board[i][j+1] == word.charAt(index)){
            used[i][j+1] = true;
            if (dfs(word,index+1,board,used,row,col,i,j+1)) return true;
            used[i][j+1] = false;
        }
        return false;
    }

}
