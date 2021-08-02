package com.coding;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * 作者: Administrator
 * 时间: 2021/7/27 18:34
 * 描述: 测试
 */
public class NiuKeSolutionTest {

    NiuKeSolution solution = new NiuKeSolution();


    @Test
    public void LIS() {
        int[] arr = new int[]{1,3,8,6,5,2,5};
        int[] lis = solution.LIS(arr);
        System.out.println(Arrays.toString(lis));
    }

    @Test
    public void threeSum(){
        int[] num = {-2,0,1,1,2};
        ArrayList<ArrayList<Integer>> arrayLists = solution.threeSum(num);
        System.out.println(arrayLists);
    }
    @Test
    public void maxWater(){
        int[] arr = {4,5,1,3,2};
        long l = solution.maxWater(arr);
        System.out.println(l);
    }
    @Test
    public void LCS(){
        String s1 = "abc";
        String s2 = "abc";
        String lcs = solution.LCS(s1, s2);
        System.out.println(lcs);
    }
}