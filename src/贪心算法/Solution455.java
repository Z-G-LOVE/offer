package 贪心算法;

import java.util.Arrays;

/**
 * @作者: one者天下
 * @时间: 2021/4/14 20:57
 * @描述: leetcode455 分发饼干
 */
public class Solution455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child = 0;
        int cookie = 0;
        while (child < g.length && cookie < s.length){
            if (g[child] <= s[cookie++]) child++;
        }
        return child;
    }
}
