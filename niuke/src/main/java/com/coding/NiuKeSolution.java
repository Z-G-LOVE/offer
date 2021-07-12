package com.coding;

import org.springframework.stereotype.Component;

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

    public int NumberToSum(char num1,char num2,char ope){
        int number1 = Integer.parseInt(num1+"");
        int number2 = Integer.parseInt(num2+"");
        return switch (ope) {
            case '*' -> number1 * number2;
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            default -> Integer.MAX_VALUE;
        };
    }
}
