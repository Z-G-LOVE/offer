package 剑指offer;

import org.junit.Test;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author : one者天下
 * @时间: 2021/5/26 19:22
 * @描述: 剑指offer
 */
public class Solution {
    /**
     * 二维数组的查找
     *
     * @param target 目标
     * @param array  二维数组
     * @return 返回二维数组是否存在target
     */
    public boolean Find(int target, int[][] array) {
        if (array == null || array[0] == null || array[0].length == 0) return false;
        for (int[] ints : array) {
            if (ints[ints.length - 1] == target || ints[0] == target) return true;
            if (ints[0] < target && ints[ints.length - 1] > target) {
                int left = 0, right = ints.length - 1;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (ints[mid] == target) return true;
                    else if (ints[mid] > target) right = mid - 1;
                    else left = mid + 1;
                }
            }
        }
        return false;
    }

    @Test
    public void testFind() {
//        int[][] arrays = new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int target = 22;

        int[][] arrays = new int[][]{{}, {}, {}};
        boolean find = Find(target, arrays);
        System.out.println(find);
    }

    /**
     * 替换字符串中的空格
     *
     * @param s 要替换的字符串
     * @return 返回将空格替换成%20的字符串
     */
    public String replaceSpace(String s) {
        if (" ".equals(s)) return "%20";
        if (s == null || "".equals(s)) return s;
        String res = "";
        boolean tag = true;
        if (s.charAt(s.length() - 1) == ' ') {
            s = s + "a";
            tag = false;
        }
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (i == split.length - 1) {
                if (tag) res = res + split[i];
                continue;
            }
            res = res + split[i] + "%20";
        }
//        int i = s.length()-1;
// while (i > 0 && s.charAt(i) == ' '){
//     i--;
//     res = res + "%20";
// }
        return res;
    }

    @Test
    public void testReplaceSpace() {
        String s = "";
        String s1 = replaceSpace(s);
        System.out.println(s1);
//        String s = " age   hello ";
//        String[] s1 = s.split(" ");
//        for (String s2 : s1) {
//            System.out.println(s2);
//        }
//        System.out.println(s1.length);
    }

    /**
     * 倒叙输出链表节点的值
     *
     * @param listNode 输入的链表
     * @return 返回一个List
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        if (listNode == null) return list;
        ListNode node = listNode;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            res.add(list.get(i));
        }
        return res;
    }

    /**
     * @author one者天下
     * 链表结构
     */
    private static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    private final static Map<Integer, Integer> CreateTreeMap = new HashMap<>();

    /**
     * 根据前序遍历和中序遍历的数组重构一个二叉树
     *
     * @param pre 前序遍历的数组
     * @param in  中序遍历的数组
     * @return 返回二叉树的根节点
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0) return null;
//        for (int i = 0; i < in.length; i++) {
//            CreateTreeMap.put(in[i],i);
//        }
//        return createTree(pre,0,pre.length,0);
        return help(pre, in, 0, in.length - 1, 0, pre.length);
    }

    /**
     * 递归实现二叉树的重构
     *
     * @param pre      前序遍历的数组
     * @param in       当前中序遍历的数组
     * @param left     当前中序遍历的左索引
     * @param right    当前中序遍历的右索引
     * @param preStart 当前前序遍历的开始索引
     * @param preEnd   当前前序遍历的结束索引
     * @return 返回子树的根节点
     */
    private TreeNode help(int[] pre, int[] in, int left, int right, int preStart, int preEnd) {
        if (preStart == preEnd) return null;
        int rootValue = pre[preStart];
        TreeNode root = new TreeNode(rootValue);
        int leftTemp = left;
        int rightTemp = right;
        int mid = (left + right) >> 1;
        while (leftTemp < rightTemp) {
            if (in[leftTemp] == rootValue) {
                mid = leftTemp;
                break;
            }
            if (in[rightTemp] == rootValue) {
                mid = rightTemp;
                break;
            }
            mid = (leftTemp + rightTemp) >> 1;
            if (in[mid] == rootValue) break;
            else if (in[mid] < rootValue) leftTemp = mid + 1;
            else rightTemp = mid;
        }
        int count = mid - left;
        TreeNode leftNode = help(pre, in, left, mid - 1, preStart + 1, preStart + count + 1);
        TreeNode rightNode = help(pre, in, mid + 1, right, preStart + count + 1, preEnd);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    /**
     * 根据前序遍历和中序遍历递归创建一颗二叉树
     *
     * @param pre      前序遍历数组
     * @param preStart 当前前序遍历的开始索引
     * @param preEnd   当前前序遍历的结束索引
     * @param inStart  当前中序遍历的开始索引
     * @return 返回创建好的子树的根节点
     */
    private TreeNode createTree(int[] pre, int preStart, int preEnd, int inStart) {
        if (preStart == preEnd) return null;
        int rootValue = pre[preStart];
        TreeNode root = new TreeNode(rootValue);
        // 获取根节点在中序遍历数组的索引位置
        int rootIndex = CreateTreeMap.get(rootValue);
        // 计算前序遍历数组的起始索引
        int preCount = rootIndex - inStart;
        root.left = createTree(pre, preStart + 1, preStart + preCount + 1, inStart);
        root.right = createTree(pre, preStart + preCount + 1, preEnd, rootIndex + 1);
        return root;
    }

    @Test
    public void testReConstructBinaryTree() {
        int[] pre = new int[]{3, 9, 20, 15, 7};
        int[] in = new int[]{9, 3, 15, 20, 7};
        TreeNode root = reConstructBinaryTree(pre, in);
//        System.out.println(root.val);
        dfsPrint(root);
        System.out.println();
        inPrint(root);

    }

    /**
     * @author one者天下
     * 二叉树结构
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 前序遍历二叉树
     *
     * @param root 二叉树的根节点
     */
    private void dfsPrint(TreeNode root) {
        if (root == null) return;
        System.out.println(root.val);
        dfsPrint(root.left);
        dfsPrint(root.right);
    }

    /**
     * 中序遍历二叉树
     *
     * @param root 二叉树的根节点
     */
    private void inPrint(TreeNode root) {
        if (root == null) return;
        inPrint(root.left);
        System.out.println(root.val);
        inPrint(root.right);
    }

    /**
     * 用两个栈模拟队列的push()和 pop()
     */
    private static class LinkQueue {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            while (!stack2.isEmpty()) stack1.push(stack2.pop());
            stack1.push(node);
        }

        public int pop() {
            while (!stack1.isEmpty()) stack2.push(stack1.pop());
            if (stack2.isEmpty()) throw new RuntimeException("队列为空");
            return stack2.pop();
        }
    }

    /**
     * 找出旋转数组中最小的数字
     *
     * @param array 旋转数组
     * @return 返回最小的数字
     */
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 1) return array[0];
        int left = 0, right = array.length - 1, mid = 0;
        while (left < right - 1) {
            mid = (left + right) >> 1;
//            if (mid == left) break;
//            if (array[mid] > array[right]) left = mid + 1;
//            else if (array[mid] < array[right]) right = mid;
            if (array[mid] > array[left]) left = mid;
            else if (array[mid] < array[right]) right = mid;
//            else left--;
        }
        return array[right];
    }

    @Test
    public void testMinNumberInRotateArray() {
        int[] arrays = new int[]{6, 7, 8, 9, 1, 2, 3, 4, 5};
        System.out.println(minNumberInRotateArray(arrays));
    }

    // 台阶的跳法
    private int count = 0;

    /**
     * 跳台阶
     *
     * @param target n阶台阶
     * @return 返回有多少种跳法
     */
    public int jumpFloor(int target) {
//        helpJumpFloor(target);
//        return count;
        Map<Integer, Integer> map = new HashMap<>();
        return helpJumpFloor(target, map);
//        int[] dp = new int[target+1];
//        return helpJumpFloor(target,dp);
    }

    /**
     * 递归实现
     *
     * @param target n阶台阶
     */
    private void helpJumpFloor(int target) {
        if (target <= 0) {
            if (target == 0) count++;
            return;
        }
        helpJumpFloor(target - 1);
        helpJumpFloor(target - 2);
    }

    /**
     * 记忆优化的递归实现
     *
     * @param target n阶台阶
     * @param map    记录第n阶台阶的跳法总数
     * @return 返回n阶台阶的跳法总数
     */
    private int helpJumpFloor(int target, Map<Integer, Integer> map) {
        if (target == 1) map.put(1, 1);
        if (target == 2) map.put(2, 2);
        if (map.containsKey(target)) return map.get(target);
        map.put(target, helpJumpFloor(target - 1, map) + helpJumpFloor(target - 2, map));
        return map.get(target);
    }

    //    private int helpJumpFloor(int target,int[] dp){
//        dp[1] = 1;
//        dp[2] = 2;
//        for (int i = 3; i < dp.length; i++) {
//            dp[i] = dp[i-1] + dp[i-2];
//        }
//        return dp[target];
//    }
    @Test
    public void testJumpFloor() {
        System.out.println(jumpFloor(9));
    }

    /**
     * @param target n阶台阶
     * @return 返回跳法的总数
     */
    public int jumpFloorII(int target) {
        if (target == 1) return 1;
        return 2 << (target - 2);
    }

    @Test
    public void testJumpFloorII() {
        System.out.println(jumpFloorII(2));
//        int i = 2;
//        i = i << 1;
//        System.out.println(i);
    }

    /**
     * 矩阵覆盖
     *
     * @param target 矩阵的宽度
     * @return 返回矩阵的覆盖的种类
     */
    public int rectCover(int target) {
        Map<Integer, Integer> map = new HashMap<>();
        return helpRectCover(target, map);
    }

    /**
     * 递归实现矩阵覆盖
     *
     * @param target 矩阵的宽度
     * @param map    用于记忆优化,防止重复计算
     * @return 返回覆盖的方法数
     */
    private int helpRectCover(int target, Map<Integer, Integer> map) {
        if (target == 1) map.put(1, 1);
        if (target == 2) map.put(2, 2);
        if (target == 0) map.put(0, 0);
        if (map.containsKey(target)) return map.get(target);
        map.put(target, helpRectCover(target - 1, map) + helpRectCover(target - 2, map));
        return map.get(target);
    }

    @Test
    public void testRectCover() {
        System.out.println(rectCover(0));
        System.out.println(rectCover(1));
        System.out.println(rectCover(4));
        System.out.println(rectCover(5));
    }

    /**
     * 二进制中1的个数
     * @param n 十进制数
     * @return 返回n的32位二进制数的1的个数
     */
    public int NumberOf1(int n) {
        if (n == 0) return 0;
        else if (n == Integer.MIN_VALUE) return 1;
        else {
            int num = Math.abs(n);
            int count = 0;
            while (num > 0) {
                count ++;
                num &= (num-1);
            }
            if (n > 0) return count;
            else if ((n & 1) == 1) return 32-count + 1;
            else return 32 - count ;
        }
    }
    @Test
    public void testNumberOf1(){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(NumberOf1(-1234567));
        System.out.println(-1 & 1);
    }
}
