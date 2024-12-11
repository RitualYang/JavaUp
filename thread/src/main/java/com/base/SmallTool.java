package com.base;

/**
 * @author wty
 * @date 2022/3/23 22:44
 */
public class SmallTool {

    public static void printTimeAndThread(String tag) {
        System.out.printf("time:%d,threadName:%s,tag:%s%n", System.currentTimeMillis(), Thread.currentThread().getName(), tag);
    }

    public static void sleepMillis(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
