package 动态规划;

import org.junit.Test;

/**
 * @作者: one者天下
 * @时间: 2021/4/12 21:41
 * @描述: leetcode877 石子游戏
 */
public class Solution877 {
    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        for (int i = 0; i < dp.length-1; i++) {
            dp[i][i+1] = Math.abs(piles[i] - piles[i+1]);
        }
        for (int i = dp.length-3; i >= 0 ; i--) {
            for (int j = i+1; j < dp.length ; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i+1][j],piles[j] - dp[i][j-1]);
            }
        }
        return dp[0][dp.length-1] > 0;
    }

    @Test
    public void test(){
        int[] piles = new int[]{1,5,232,7};
        System.out.println(stoneGame(piles));
    }
}
