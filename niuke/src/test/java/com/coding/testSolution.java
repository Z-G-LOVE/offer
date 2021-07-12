package com.coding;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.naming.Context;

import static org.junit.Assert.*;

/**
 * @作者: Administrator
 * @时间: 2021/7/12 23:18
 * @描述: TODO
 */
public class testSolution {

    private final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private final NiuKeSolution solution = context.getBean("solution",NiuKeSolution.class);

    @Test
    public void numberToSum() {
        int res = solution.NumberToSum('2', '2', '*');
        System.out.println(res);
    }

    @Test
    public void solve() {
    }
}