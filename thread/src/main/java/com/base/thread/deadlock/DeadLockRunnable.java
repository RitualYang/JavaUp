package com.base.thread.deadlock;

/**
 * @author WTY
 */
public class DeadLockRunnable implements Runnable {
    /** 决定线程走向的标记 */
    private int flag;
    /** 锁对象1 */
    private static Object obj1 = new Object();
    /** 锁对象2 */
    private static Object obj2 = new Object();

    public DeadLockRunnable(int flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        if(flag == 1){
            //线程1执行代码：
            synchronized (obj1){
                System.out.println(Thread.currentThread().getName()+"已获取到资源obj1,请求obj2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName()+"已经获取到obj1和obj2！");
                }
            }
        } else {
            //线程2执行代码
            synchronized (obj2){
                System.out.println(Thread.currentThread().getName()+"已获取到资源obj2,请求obj1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName()+"已经获取到obj1和obj2！");
                }
            }
        }
    }
}