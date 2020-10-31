package com.base.thread.volat;

/**
 * volatile ：当多个线程进行操作共享数据时，可以保证内存中的数据可见。
 * 相较于 synchronized 是一种较为轻量级的同步策略。
 * 注意：
 * 1. volatile 不具备“互斥性”
 * 2. volatile 不能保证变量的“原子性”
 *
 * @author wty
 * @date 2020/5/11 21:19
 */
public class VolatileMain {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td,"ThreadDemo").start();
        while (true) {
            System.out.println(Thread.currentThread().getName() + "--flag=" + td.isFlag());
            if (td.isFlag()) {
                System.out.println("------------------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println(Thread.currentThread().getName() + "--flag=" + isFlag());

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
