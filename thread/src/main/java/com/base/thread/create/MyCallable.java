package com.base.thread.create;

import java.util.concurrent.Callable;

/**
 * @author wty
 * @date 2020/5/7 23:06
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " 执行了：" + System.currentTimeMillis() + "执行次数：" + i);
        }
        return "MyCallable";
    }
}
