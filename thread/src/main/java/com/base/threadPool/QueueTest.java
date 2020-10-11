package com.base.threadPool;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

/**
 * @author: WTY
 * @date: 2019-12-25
 * @desc:
 */
public class QueueTest {

    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        /**
         * 基于数组有界队列,容量为10
         */
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("向队列添加值:" + i);
        }
    }

    @Test
    public void testLinkedBlockingQueue() throws InterruptedException {
        /**
         * 基于链表的有界/无界阻塞队列,容量为10
         */
        LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>(10);
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("向队列添加值:" + i);
        }
    }

    @Test
    public void testSynchronousQueue() throws InterruptedException {
        /**
         * 同步移交阻塞队列
         */
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                queue.put(1);
                System.out.println("向队列添加值:" + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
