package com.base.thread.inform;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WTY
 */
public class ConditionAndObjectMain {

    private int i = 0;//要打印的数
    private Object obj = new Object();
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args) {
        final ConditionAndObjectMain oddEvenMain = new ConditionAndObjectMain();
        /**
         * condition.await() 必须和Lock（互斥锁/共享锁）配合使用
         * condition.await() 必须通过 condition.signal() 方法进行唤醒
         */
        oddEvenMain.useLock(oddEvenMain);
        /**
         * object.wait||notify 需要在synchronized代码块中使用
         * object.wait()必须要通过Nodify()方法进行唤醒
         */
        //oddEvenMain.useSync(oddEvenMain);
    }

    public void useLock(ConditionAndObjectMain oddEvenMain) {
        //1.开启奇数线程打印
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenMain.odd();
            }
        });
        //2.开启偶数线程打印
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenMain.even();
            }
        });

        thread1.start();
        thread2.start();
    }

    public void useSync(ConditionAndObjectMain oddEvenMain) {
        //1.开启奇数线程打印
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenMain.oddSync();
            }
        });
        //2.开启偶数线程打印
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenMain.evenSync();
            }
        });
        thread1.start();
        thread2.start();
    }

    /**
     * 奇数打印方法,由奇数线程调用
     */
    public void odd() {
        //1.判断i是否小于100,
        while (i < 100) {
            lock.lock();
            try {
                //2.<10打印奇数
                if (i % 2 == 1) {
                    System.out.println("奇数：" + i);
                    i++;
                    //obj.notify();//唤醒偶数线程打印
                    condition.signal();//唤醒偶数线程打印
                } else {
                    try {
                        //obj.wait();//等待偶数线程打印完毕
                        condition.await();//等待偶数线程打印完毕
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 偶数打印方法,由偶数线程调用
     */
    public void even() {
        //1.判断i是否小于100,
        while (i < 100) {
            lock.lock();
            try {
                //2.<10打印偶数
                if (i % 2 == 0) {
                    System.out.println("偶数：" + i);
                    i++;
                    //obj.notify();//唤醒奇数线程打印
                    condition.signal();//唤醒奇数线程打印
                } else {
                    try {
                        //obj.wait();//等待奇数线程打印完毕
                        condition.await();//等待奇数线程打印完毕
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }

        }
    }

    public void oddSync() {
        //1.判断i是否小于100,
        while (i < 100) {
            synchronized (obj) {
                //2.<10打印奇数
                if (i % 2 == 1) {
                    System.out.println("奇数：" + i);
                    i++;
                    obj.notify();//唤醒偶数线程打印
                } else {
                    try {
                        obj.wait();//等待偶数线程打印完毕
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void evenSync() {
        //1.判断i是否小于100,
        while (i < 100) {
            synchronized (obj) {
                //2.<10打印偶数
                if (i % 2 == 0) {
                    System.out.println("偶数：" + i);
                    i++;
                    obj.notify();//唤醒奇数线程打印
                } else {
                    try {
                        obj.wait();//等待奇数线程打印完毕
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}