package com.coding;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @作者: Administrator
 * @时间: 2021/7/12 22:39
 * @描述: 牛客算法
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
}
