package com.base.thread.create;

/**
 * @author WTY
 * @Date 2020/5/7 22:56
 */
public class MyThread extends Thread {
    public MyThread(String myThread) {
        super(myThread);
    }

    @Override
    public void run() {
        for (int i = 0; i< 10; i++){
            System.out.println(Thread.currentThread().getName() + " 执行了：" + System.currentTimeMillis() + "执行次数：" + i);
        }
    }
}
