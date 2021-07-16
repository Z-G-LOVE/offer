package com.coding;

import org.junit.Test;

import java.util.Arrays;

/**
 * @作者: Administrator
 * @时间: 2021/7/14 22:04
 * @描述: TODO
 */
public class Test1 {
    @Test
    public void test1(){
        String s1 = "(-1)";
        String re = "[()]*[*+-][()]*";
        String[] split = s1.split(re);
        System.out.println(Arrays.toString(split));
    }
}
