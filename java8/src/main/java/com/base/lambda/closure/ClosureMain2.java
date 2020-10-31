package com.base.lambda.closure;

import java.util.function.Consumer;

/**
 * @author wty
 * @date 2020/4/24 19:11
 */
public class ClosureMain2 {
    public static void main(String[] args) {
        int a = 10;//默认添加 final修饰
        Consumer<Integer> c = element -> {
            System.out.println(a);
        };
        c.accept(111);
    }
}
