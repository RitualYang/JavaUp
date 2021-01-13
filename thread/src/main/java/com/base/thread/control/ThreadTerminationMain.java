package com.base.thread.control;

import java.util.concurrent.TimeUnit;

/**
 * 线程终止
 * @author wty
 * @date 2020/11/1 10:27
 */
public class ThreadTerminationMain {
    private static int i;
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) { //默认情况下
                //isInterrupted返回 false、通过thread.interrupt 变成了true
                i++;
            }
            System.out.println("Num:" + i);
        }, "interrupt");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
