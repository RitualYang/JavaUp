package com.base.thread.status;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * await: 使当前线程释放锁对象,进入等待列,等待被唤醒
 * signal: 唤醒一个等待队列中的线程
 * signalAll: 唤醒全部等待队列中的线程
 * 注意：
 *  1.以上方法必须与lock一起使用
 *  2.await与signal必须成对使用
 *  3.必须先执行.await,在执行signal
 * @author wty
 * @date 2021/1/24 21:08
 */
public class AwaitSignalMain {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("执行线程a1");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程a1执行完毕");
            } finally{
                lock.unlock();
            }
        },"a1").start();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("执行线程b1");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                condition.signal();
                System.out.println("唤醒线程a1");
            }finally {
                lock.unlock();
            }
        },"b1").start();
    }
}
