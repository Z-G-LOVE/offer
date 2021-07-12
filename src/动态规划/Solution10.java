package 动态规划;

import org.junit.Test;

import java.util.Arrays;

/**
 * @作者: one者天下
 * @时间: 2021/4/11 23:23
 * @描述: 正则表达式匹配 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 */
public class Solution10 {

    private boolean[][] dp;

    public boolean isMatch(String s, String p) {
        char[] c1 = s.toCharArray();
        char[] c2 = p.toCharArray();
        dp = new boolean[c2.length+1][c1.length+1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            if (c2[i-1] == '*'){
                if (i < 3) dp[i][0]  = true;
                else if (dp[i-1][0] || dp[i-2][0]) dp[i][0] = true;
            }
            for (int j = 1; j < dp[0].length; j++) {
                if (c2[i-1] == '*') {
                    if (j > 1){
                        if ((c1[j-2] == c1[j-1] && (dp[i-1][j-1] || dp[i-2][j])) || dp[i-1][j]) dp[i][j] = true;
                    }else {
                        if (dp[i-1][j] || dp[i-2][j]) dp[i][j] = true;
                    }
                }
                else if ((c2[i-1] == '.' || c2[i-1] == c1[j-1]) && dp[i-1][j-1]) dp[i][j] = true;
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public void demo(String s,String p){
        char[] c1 = s.toCharArray();
        char[] c2 = p.toCharArray();
        dp = new boolean[c2.length+1][c1.length+1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            if (c2[i-1] == '*'){
                if (i < 3) dp[i][0]  = true;
                else if (dp[i-1][0] || dp[i-2][0]) dp[i][0] = true;
            }
            for (int j = 1; j < dp[0].length; j++) {
                if (c2[i-1] == '*') {
                    if (c2[i-2] == '.' && (dp[i-1][j] || dp[i][j-1])) dp[i][j] = true;
                    if (j > 1){
                        if ((c1[j-2] == c1[j-1] && (dp[i-1][j-1])) || dp[i-2][j] || dp[i-1][j]) dp[i][j] = true;
                    }else {
                        if (dp[i-1][j] || dp[i-2][j]) dp[i][j] = true;
                    }
                }
                else if ((c2[i-1] == '.' || c2[i-1] == c1[j-1]) && dp[i-1][j-1]) dp[i][j] = true;
            }
        }
    }

    @Test
    public void test(){
        String s = "mississippi";
        String p = "mis*is*ip*.";
        demo(s,p);
        for (boolean[] booleans : dp) {
            System.out.println(Arrays.toString(booleans));
        }
    }

    @Test
    public void test1(){
        String s = "aaa";
        String p = "ab*ac*a";
        demo(s,p);
        for (boolean[] booleans : dp) {
            System.out.println(Arrays.toString(booleans));
        }
    }

    @Test
    public void test2(){
        String s = "mississippi";
        String p = "mis*is*p*.";
        demo(s,p);
        for (boolean[] booleans : dp) {
            System.out.println(Arrays.toString(booleans));
        }
    }
    @Test
    public void test3(){
        String s = "aaa";
        String p = ".*";
        demo(s,p);
        for (boolean[] booleans : dp) {
            System.out.println(Arrays.toString(booleans));
        }
    }

}
