package 动态规划;

import org.junit.Test;

import java.util.Arrays;

/**
 * @作者: one者天下
 * @时间: 2021/4/11 16:23
 * @描述: 通配符字符串匹配
 */
public class Solution44 {

    boolean[][] dp;

    public boolean isMatch(String s, String p) {
        char[] c1 = s.toCharArray();
        char[] c2 = p.toCharArray();
        dp = new boolean[c2.length+1][c1.length+1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            if (c2[i-1] == '*')dp[i][0] = true;
            for (int j = 1; j < dp[0].length ; j++) {
                if (c2[i-1] == '*' && dp[i-1][j-1]){
                    dp[i][j] = true;
                }else if ((c2[i-1] == '?' || c2[i-1] == c1[j-1]) && dp[i-1][j-1]) {
                    dp[i][j] = true;
                    break;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public void demo(String text1,String text2){
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        dp = new boolean[c2.length+1][c1.length+1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            if (c2[0] == '*') dp[1][0] = true;
            if (c1.length == 0) {
                if (c2[i - 1] == '*') dp[i][0] = true;
                else break;
            } else {
                if (c2[i-1] == '*' && dp[i-1][0]) dp[i][0] = true;
                for (int j = 1; j < dp[0].length; j++) {
                    if (c2[i - 1] == '*' && (dp[i - 1][j - 1] || dp[i][j - 1] || dp[i - 1][j])) {
                        dp[i][j] = true;
                    } else if ((c2[i - 1] == '?' || c2[i - 1] == c1[j - 1]) && (dp[i - 1][j - 1])) {
                        dp[i][j] = true;
                    }
                }
            }
        }
    }

    @Test
    public void test1(){
        String text1 = "acdcb";
        String text2 = "a*c?b";
        demo(text1,text2);
        for (boolean[] booleans : dp) {
            System.out.println(Arrays.toString(booleans));
        }
    }

    @Test
    public void test(){
        String s = "";
        char[] chars = s.toCharArray();
        System.out.println(Arrays.toString(chars));
        System.out.println(chars.length);
    }
}
