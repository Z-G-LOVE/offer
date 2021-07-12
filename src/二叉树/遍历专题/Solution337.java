package 二叉树.遍历专题;

import org.junit.Test;

import java.util.*;

/**
 * @作者: one者天下
 * @时间: 2021/4/27 10:31
 * @描述: 打家劫舍III
 */
public class Solution337 {

    //模拟一个dp[]数组,dp[i]表示二叉树的第i层所能得到的最大金额
    private final List<Integer> list = new ArrayList<>();


    // 失败的解法
    public int rob1(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int cur = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            int count = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                count += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            if (list.size() < 2)list.add(count);
            else if (list.size() < 3){
                count += list.get(cur-2);
                list.add(count);
            }else {
                int max = Math.max(list.get(cur-2),list.get(cur-3));
                count += max;
                list.add(count);
            }
            cur++;
        }
        return Math.max(list.get(list.size()-2),list.get(list.size()-1));
    }

    // 解决方案一，暴力递归->超时：原因是重复计算
    // 两种偷钱的方式：
    // 爷爷偷到的钱加上4个孙子偷来的钱
    // 两个儿子偷来的钱
    public int rob2(TreeNode root) {
        if (root == null) return 0;
        int count = root.val;
        if (root.left != null) {
            count += rob2(root.left.left) + rob2(root.left.right);
        }
        if (root.right != null) {
            count += rob2(root.right.left) + rob2(root.right.right);
        }
        return Math.max(count,rob2(root.left) + rob2(root.right));
    }

    // 解决方案二：记忆优化的递归
    public int rob(TreeNode root){
        Map<TreeNode,Integer> map = new HashMap<>();
        return robb(root , map);
    }
    // 进行递归
    public int robb(TreeNode root,Map<TreeNode,Integer> map){
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);
        int money = root.val;
        if (root.left != null) money += robb(root.left.left,map) + robb(root.left.right,map);
        if (root.right != null) money += robb(root.right.left,map) + robb(root.right.right,map);
        int res = Math.max(money,robb(root.left,map) + robb(root.right,map));
        return res;
    }
    public int rob3(TreeNode root){
        int[] dp = dp(root);
        return Math.max(dp[0],dp[1]);
    }

    // 解决方案三：动态规划
    public int[] dp(TreeNode root){
        if (root == null) return new int[2];
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        res[1] = left[0] + right[0] + root.val;
        return res;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.left = node1;
        node1.left = node2;
        node2.left = node3;
        rob1(root);
    }
}
