package com.coding;

import org.junit.Test;

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
}