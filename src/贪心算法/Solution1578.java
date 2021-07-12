package 贪心算法;

import org.junit.Test;

/**
 * @作者: one者天下
 * @时间: 2021/4/16 21:44
 * @描述: 避免重复字母的最小成本
 */
public class Solution1578 {
    public int minCost(String s, int[] cost) {
        if (s.length() == 1)return 0;
        int l = 0;
        int r = l+1;
        int mincost = 0;
        while (r < s.length()){
            if (s.charAt(l) == s.charAt(r)){
                if (cost[l] <= cost[r]){
                    mincost = mincost + cost[l];
                    l++;
                    r++;
                }else {
                    mincost = mincost + cost[r];
                    r++;
                }
            }else {
                if (r-l > 1){
                    l = r;
                    r++;
                }else {
                    l++;
                    r++;
                }
            }
        }
        return mincost;
    }

    @Test
    public void test(){
        String s = "aaaaaaaaaaaaaa";
        int[] cost = new int[]{1,3,6,5,4,5,4,4,2,8,3,10,6,6};
        System.out.println(minCost(s, cost));
    }
}
