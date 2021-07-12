package 贪心算法;

import org.junit.Test;

/**
 * @作者: one者天下
 * @时间: 2021/4/14 21:24
 * @描述: 跳跃游戏II
 */
public class Solution45 {
    private int num = 0;
    public int jump(int[] nums) {
        help(nums,1,nums.length-1);
        return num;
    }

    public void help(int[] nums,int count,int index){
        if (index == 0)return;
        for (int i = 0; i < nums.length; i++) {
            if (i + nums[i] >= index){
                num = count;
                help(nums,count+1,i);
                break;
            }
        }
    }

    @Test
    public void test(){
        int[] nums = new int[]{1,3,1,1,5,8};
        int count = 1;
        help(nums,count,nums.length-1);
        System.out.println(num);
    }
}
