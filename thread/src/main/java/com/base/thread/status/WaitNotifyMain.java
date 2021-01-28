package com.base.thread.status;

import java.util.concurrent.TimeUnit;

/**
 * wait: 使当前线程释放锁对象,进入等待列,等待被唤醒
 * notify: 唤醒一个等待队列中的线程
 * notifyAll: 唤醒全部等待队列中的线程
 * 注意：
 *  1.以上方法必须在同步代码块中执行
 *  2.wait与notify必须成对使用
 *  3.必须先执行wait,在执行notify
 * @author wty
 * @date 2021/1/24 20:56
 */
public class WaitNotifyMain {
    static Object a1 = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a1) {
                System.out.println("执行线程a1");
                try {
                    a1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程a1执行完毕");
            }
        },"a1").start();
        new Thread(() -> {
            synchronized (a1) {
                System.out.println("执行线程b1");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a1.notify();
                System.out.println("唤醒线程a1");
            }
        },"b1").start();
    }

}
