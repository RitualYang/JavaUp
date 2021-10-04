package com.wty;

import java.math.BigDecimal;

/**
 * @author wty
 * @date 2021/9/18 21:48
 * 描述: 小数测试
 */
public class DoubleTest {
    public static void main(String[] args) {
        int i = 900;
        while (i-- > 0) {
            multiply();
        }
    }

    private static void multiply() {
        BigDecimal bigDecimal = new BigDecimal("3.4444");
        BigDecimal sum = BigDecimal.ONE;
        int i = 900;
        while (i -- > 0) {
            sum = sum.multiply(bigDecimal);
        }
        System.out.println(sum);
    }
}
