package 动态规划;

import org.junit.Test;

import java.util.Arrays;

/**
 * @作者: one者天下
 * @时间: 2021/4/12 21:19
 * @描述: leetcode486 预测赢家
 */
public class Solution486 {
    private int[][] dp;
    public boolean PredictTheWinner(int[] nums) {
        // dp[i][j] 表示 数组nums i -> j 的分差.
        dp = new int[nums.length][nums.length];
        for (int i = 0; i < dp.length; i++) dp[i][i] = nums[i];
        for (int i = nums.length-2; i >= 0 ; i--)
            for (int j = i+1; j < dp.length; j++) dp[i][j] = Math.max((nums[i] - dp[i+1][j]),(nums[j] - dp[i][j-1]));
        return dp[0][dp.length-1] >= 0;
    }
    @Test
    public void test(){
        int[] nums = new int[]{1,5,2};
        PredictTheWinner(nums);
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
