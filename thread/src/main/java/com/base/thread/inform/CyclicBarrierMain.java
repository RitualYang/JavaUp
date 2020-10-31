package com.base.thread.inform;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier实现让一组线程等待至某个状态之后再全部同时执行。
 * 底层通过ReentrantLock和Condition实现的。
 *
 * @author wty
 */
public class CyclicBarrierMain {
    //参数是参与CyclicBarrier的线程数
    private final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public void startThread() {
        //1.打印线程准备启动
        String name = Thread.currentThread().getName();
        System.out.println(name + "正在准备。。。");
        //2.调用CyclicBarrier的await方法等待其他线程全部准备完成
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        //3.打印线程启动完毕信息
        System.out.println(name + "准备完毕,起跑：" + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        final CyclicBarrierMain cyclicBarrierMain = new CyclicBarrierMain();
        Thread thread1 = new Thread(cyclicBarrierMain::startThread, "选手1");
        Thread thread2 = new Thread(cyclicBarrierMain::startThread, "选手2");
        Thread thread3 = new Thread(cyclicBarrierMain::startThread, "选手3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}