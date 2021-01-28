package com.base.thread.status;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport是用来创建锁和其他同步类的基本线程阻塞原语.
 *
 * LockSupport是一个线程阻塞工具类,所有的方法都是静态方法,可以让线程
 * 在任意位置阻塞，阻塞之后也有对应的唤醒方法。归根结底，LockSupport
 * 调用的Unsafe中的native代码。
 *
 * LockSupport提供park和unpark方法实现阻塞线程和解除线程阻塞的过程
 *
 * LockSupport和每个使用它的线程都有一个许可(permit)关联。permit相当于
 * 0/1的开关，默认是0.
 * 调用一次unpark就加1变成1.
 * 调用一次park会消费permit，也就是将1变成0，同时park立即返回。
 * 如再此调用park会变成阻塞(因为permit为零了会阻塞在这里，一直到permit变为0)
 * 这时调用unpark会把permitb置为1。
 * 每个线程都有一个相关的permit，permit最多只有一个，重复调用unpark也不会积累凭证。
 *
 * 线程阻塞需要消耗permit,这个permit最多只有1个
 *
 * 当调用park方法时
 *      如果有凭证,则会直接消耗掉这个凭证然后正常退出。
 *      如果无凭证，则必须阻塞等待凭证可用
 * 当调用unpark方法时
 *      它会增加一个凭证，但凭证最多只有1个，累加无效
 * @author wty
 * @date 2021/1/24 21:22
 */
public class ParkUnParkMain {
    public static void main(String[] args) {
        Thread a1 = new Thread(() -> {
            System.out.println("执行线程a1");
            LockSupport.park();
            System.out.println("线程a1执行完毕");
        }, "a1");
        a1.start();
        new Thread(() -> {
            System.out.println("执行线程b1");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(a1);
            System.out.println("唤醒线程a1");
        },"b1").start();
    }
}
