package com.base.lambda.closure;

import java.util.function.Supplier;

/**
 * @author wty
 * @date 2020/4/24 19:11
 */
public class ClosureMain {
    public static void main(String[] args) {
        int n = getNumber().get();
        System.out.println(n);
    }

    private static Supplier<Integer> getNumber() {
        //闭包提升作用域
        int num = 10;
        return () -> num;
    }
}
