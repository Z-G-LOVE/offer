package com.coding;

import com.coding.结构.TreeNode;
import org.springframework.stereotype.Component;


import java.util.*;

/**
 * 作者: Administrator
 * 时间: 2021/7/12 22:39
 * 描述: 牛客算法
 */
@Component("solution")
public class NiuKeSolution {
    /**
     * 实现整数的加减乘运算
     * @param s 表达式
     * @return 运算结果
     */
    public int solve (String s) {
        return 0;
    }
/*    public List<String> classification(String s){
        List<String> ex = new ArrayList<>();
        int bi = 0,ai = 0;// 设置前置索引和后置索引
        while (ai < s.length()){
            while (isNum(s.charAt(ai)))ai++;
            ex.add(s.substring(bi,ai));
            bi = ai;
        }
    }*/
    private boolean isNum(char s){
        return !(s == '-' || s == '+' || s == '*' || s == ')' || s == '(');
    }
    public int[] LIS (int[] arr) {
        // 创建两个数组
        int[] nums = new int[arr.length];// 记录arr的每一个元素的位置
        int[] temp = new int[arr.length];// 用于比较arr元素，并进行标记
        int tempIndex = 0;// 当前最长递增子序列的长度索引
        nums[0] = 1;
        temp[tempIndex] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int left = 0,right = tempIndex;// 二分查找temp数组中第一个大于arr[i]的元素
            if (arr[i] > temp[tempIndex]){// arr[i]大于temp的最后一个元素，直接在temp后面插入
                temp[++tempIndex] = arr[i];
                nums[i] = tempIndex+1; // 标记位置
            }else { // arr[i]小于temp数组的最后一个元素，在temp中二分查找第一个比arr[i]大的 元素并替换它
                while (left <= right){
                    int mid = (left + right) >> 1;
                    if (temp[mid] >= arr[i]) right = mid - 1;
                    else left = mid + 1;
                }
//                if (temp[tempIndex] == arr[i]) nums[i] = left;
//                else nums[i] = left + 1; // 标记位置
                temp[left] = arr[i]; // 替换
                nums[i] = left + 1;
            }
        }
        int[] res = new int[tempIndex+1];
        // 倒序遍历nums
        for (int i1 = nums.length-1; i1 >= 0; i1--) {
            if (nums[i1] == tempIndex+1) res[tempIndex--] = arr[i1];
        }
//        System.out.println(Arrays.toString(nums));
//        System.out.println(tempIndex);
        return res;
    }
//    private final ArrayList<ArrayList<Integer>> res = new ArrayList<>();
//    private final ArrayList<Integer> list = new ArrayList<>();
//    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
//        if (num.length < 3) return res;
//        Arrays.sort(num);
//        helpThreeSum(num,0,0,0);
//        return res;
//    }
//
//    private void helpThreeSum(int[] num,int start,int target,int count){
//        if (target == 0 && count == 3) {
//
//            res.add(new ArrayList<>(list));
//
//            return;
//        }
//        if (start >= num.length || count > 3) return;
//        for (int i = start; i < num.length; i++) {
//            if (start > 0 && num[start-1]==num[start]) break;
//            if (count <= 3){
//                list.add(num[i]);
//                count++;
////                if (i >= 1 && num[i-1] == num[i]) continue;？
//                helpThreeSum(num,i+1,target+num[i],count);
//                count--;
//                list.remove(list.size()-1);
//            }else break;
//        }
//    }

    /**
     *  数组中相加和为0的三元组
     * @param num 输入的数组
     * @return 返回和为0的集合
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (num == null || num.length < 3) return res;
        Arrays.sort(num);// 排序
        for (int i = 0; i < num.length-2; i++) {
            if (num[i] > 0 ) break;// 若是当前的元素大于零，因为是有序的数组，后面两个元素一定大于0，所以不存在三数之和为0.
            if (i > 0 && num[i-1] == num[i]) continue;// 去重
            int l = i+1,r = num.length-1;
            while (l < r){
                int sum = num[i] + num[l] + num[r];
                if (sum == 0){
                    ArrayList<Integer> list = new ArrayList<>(3);
                    list.add(num[i]);
                    list.add(num[l]);
                    list.add(num[r]);
                    res.add(list);
                    while (l < r && num[l] == num[l+1]) l++;
                    while (l < r && num[r] == num[r-1]) r--;
                    l++;
                    r--;
                }else if (sum > 0) r--;
                else l++;
            }
        }
        return res;
    }

    /**
     * 记录中序遍历数组的位置
     * key:中序遍历的值
     * value：中序遍历的值的索引位置
     */
    private final Map<Integer,Integer> RCBTmap = new HashMap<>();
    /**
     * 根据前序遍历数组和中序遍历数组构建一颗二叉树
     * @param pre 前序遍历的数组
     * @param in 中序遍历的数组
     * @return 返回二叉树的根节点
     */
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        for (int i = 0; i < in.length; i++) {
            RCBTmap.put(in[i],i);
        }
        return helpReConstructBinaryTree(pre,0,pre.length,0);
    }

    /**
     * 根据前序遍历数组和中序遍历数组递归构建一颗二叉树
     * @param pre 前序遍历的数组
     * @param preStart 前序遍历的起始位置
     * @param preEnd 前序遍历的结束位置
     * @param inStart 中序遍历的起始位置
     * @return 返回每次构造的子树的父节点
     */
    private TreeNode helpReConstructBinaryTree(int[] pre ,int preStart,int preEnd,int inStart){
        if (preStart == preEnd) return null;
        int rootValue = pre[preStart];
        TreeNode root = new TreeNode(rootValue);
        int rootIndex = RCBTmap.get(rootValue);// 获取当前节点在中序遍历数组中的位置
        int preCount = rootIndex - inStart;// 计算左子树的个数
        root.left = helpReConstructBinaryTree(pre,preStart+1,preStart + preCount + 1,inStart);
        root.right = helpReConstructBinaryTree(pre,preStart+preCount+1,preEnd,rootIndex+1);
        return root;
    }

    /**
     * 根据前序遍历和中序遍历数组构造一个二叉树，并输出它的右视图
     * @param xianxu 前序遍历数组
     * @param zhongxu 中序遍历数组
     * @return 返回构造后的二叉树的右视图
     */
    public int[] solve (int[] xianxu, int[] zhongxu) {
        for (int i = 0; i < zhongxu.length; i++) {
            RCBTmap.put(zhongxu[i],i);
        }
        TreeNode root = helpReConstructBinaryTree(xianxu,0,xianxu.length,0);
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            list.add(queue.peekLast().val);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pop();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (Integer integer : list) {
            res[index++] = integer;
        }
        return res;
    }

    /**
     * 岛屿数量
     * @param grid 地图
     * @return 返回该地图中岛屿的数量
     */
    public int solve (char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rowCount = grid.length,col = grid[0].length;
        int res = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int i1 = 0; i1 < col; i1++) {
                if (grid[i][i1] == '1'){
                    res++;
                    dfsSolve(grid,i,i1);
                }
            }
        }
        return res;
    }

    /**
     * 深度优先遍历岛屿，将连通的岛屿一次遍历完
     * @param grid 地图
     * @param row 坐标
     * @param col 坐标
     */
    private void dfsSolve(char[][] grid ,int row, int col){
        int rowCount = grid.length;
        int colCount = grid[0].length;
        // 设置边界
        if (row < 0 || col < 0 || row >= rowCount || col >= colCount || grid[row][col] != '1') return;
        // 表示已遍历
        grid[row][col] = '2';
        dfsSolve(grid,row+1,col);
        dfsSolve(grid,row-1,col);
        dfsSolve(grid,row,col-1);
        dfsSolve(grid,row,col+1);
    }
}
