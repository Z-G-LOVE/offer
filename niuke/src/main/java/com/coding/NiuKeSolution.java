package com.coding;

import org.springframework.stereotype.Component;

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
}
