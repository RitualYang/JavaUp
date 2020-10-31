package com.base.thread.deadlock;

/**
 * 死锁产生的必要条件
 * 以下这四个条件是死锁的必要条件,只要系统发生死锁,这些条件必然成立,而只要下述条件之一不满足,就不会发生死锁。
 * 1.互斥条件
 * 进程要求对所分配的资源（如打印机）进行排他性控制,即在一段时间内某资源仅为一个进程所占有。
 * 此时若有其他进程请求该资源,则请求进程只能等待。
 * 2.不可剥夺条件
 * 进程所获得的资源在未使用完毕之前,不能被其他进程强行夺走,即只能由获得该资源的进程自己来
 * 释放（只能是主动释放)。
 * 3.请求与保持条件
 * 进程已经保持了至少一个资源,但又提出了新的资源请求,而该资源已被其他进程占有,此时请求进
 * 程被阻塞,但对自己已获得的资源保持不放。
 * 4.循环等待条件
 * 存在一种进程资源的循环等待链,链中每一个进程已获得的资源同时被 链中下一个进程所请求。
 * 即存在一个处于等待状态的进程集合{Pl, P2, …, pn},其中Pi等 待的资源被P(i+1)占
 * 有（i=0, 1, …, n-1),Pn等待的资源被P0占有
 *
 * @author wty
 */
public class DeadLockMain {
    public static void main(String[] args) {
        //1.创建两个DeadLockRunnable实例：flag = 1；flag = 2
        DeadLockRunnable runnable1 = new DeadLockRunnable(1);
        DeadLockRunnable runnable2 = new DeadLockRunnable(2);
        //2.创建两个线程执行两个DeadLockRunnable实例
        Thread thread1 = new Thread(runnable1, "runnable1");
        Thread thread2 = new Thread(runnable2, "runnable2");
        thread1.start();
        thread2.start();
    }
}