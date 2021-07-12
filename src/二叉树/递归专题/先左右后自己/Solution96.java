package 二叉树.递归专题.先左右后自己;

import org.junit.Test;
import 二叉树.递归专题.数据结构.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者: one者天下
 * @时间: 2021/4/30 13:31
 * @描述: 不同的二叉搜索树
 */
public class Solution96 {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }

    private List<TreeNode> helper(int start,int end){
        List<TreeNode> list = new ArrayList<>();
        if (start > end){
            list.add(null);
            return list;
        }
        for (int i = start; i <= end ; i++) {
            List<TreeNode> left = helper(start,i-1);
            List<TreeNode> right = helper(i+1,end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root  = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    list.add(root);
                }
            }
        }
        return list;
    }
    private int helperCount(int start,int end){
        if (start > end) return 0;
        int count = 0;
        for (int i = start; i <= end ; i++) {
            int leftCount = helperCount(start,i-1);
            int rightCount = helperCount(i+1,end);
            count = leftCount * rightCount;
        }
        return count;
    }
    @Test
    public void test(){
        numTrees(3);
    }
}
