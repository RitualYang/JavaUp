package com.base.lambda.exercise;

/**
 * @author WTY
 * @Date 2020/4/24 17:42
 */
public class Exercise5 {
    public static void main(String[] args) {
        // 需求: 开辟一条线程,做一个数字的输出
        Thread thread = new Thread(() -> {
           for (int i = 0;i < 100;i++){
               System.out.println(i);
           }
        });
        thread.start();
    }
}
