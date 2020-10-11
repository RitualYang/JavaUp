package com.base.thread.control;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author WTY
 * @Date 2020/5/9 15:17
 */
public class ThreadAtomicMain {

    final int WHILE = 100;
    final int FOR = 1000;
    static int n;
    static AtomicInteger atomicInteger;
    static AtomicStampedReference<Integer> atomicSR;

    public static void main(String[] args) throws InterruptedException {
        ThreadAtomicMain threadAtomicMain = new ThreadAtomicMain();
        threadAtomicMain.notAtomic();
        System.out.println("---------------------");
        threadAtomicMain.atomicClass();
        System.out.println("---------------------");
        threadAtomicMain.atomicSR();
    }

    /**
     * 未保证数据原子性
     *
     * @throws InterruptedException
     */
    public void notAtomic() throws InterruptedException {
        int j = 0;
        while (j < WHILE) {
            n = 0;
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < FOR; i++) {
                        n++;
                    }
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < FOR; i++) {
                        n++;
                    }
                }
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("n的最终值是：" + n);
            j++;
        }
    }

    /**
     * 保证数据原子性
     * 但可能出现ABA问题
     *
     * @throws InterruptedException
     */
    public void atomicClass() throws InterruptedException {
        int j = 0;
        while (j < WHILE) {
            //创建原子整数,初始值为0
            atomicInteger = new AtomicInteger(0);
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < FOR; i++) {
                        //++操作
                        atomicInteger.getAndIncrement();
                    }
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < FOR; i++) {
                        atomicInteger.getAndIncrement();
                    }
                }
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("atomicInteger的最终值是：" + atomicInteger.get());
            j++;
        }
    }

    public void atomicSR() throws InterruptedException {
        int j = 0;
        while (j < WHILE) {
            //初始值为0,时间戳的初始值为0
            atomicSR = new AtomicStampedReference(0, 0);
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < FOR; i++) {
//                        n++;
                        int stamp;
                        Integer reference;
                        do {
                            stamp = atomicSR.getStamp();
                            reference = atomicSR.getReference();
                        } while (!atomicSR.compareAndSet(reference, reference + 1, stamp, stamp + 1));
                    }
                }
            };
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < FOR; i++) {
//                        n++;
                        int stamp;
                        Integer reference;
                        do {
                            stamp = atomicSR.getStamp();
                            reference = atomicSR.getReference();
                        } while (!atomicSR.compareAndSet(reference, reference + 1, stamp, stamp + 1));
                    }
                }
            };
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("AtomicStampedReference的最终值是：" + atomicSR.getReference());
            j++;
        }
    }
}
