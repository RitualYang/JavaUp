package com.base.thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WTY
 */
public class Ticket implements Runnable {
    /** 电影票数据量,默认100张 */
    private static int ticketNum = 100;
    private static Object obj = new Object();
    /**
     * lock：可重入,可判断,可公平
     * 参数是是否公平锁：
     *     true-公平锁,多个线程都公平拥有执行权；
     *     false-非公平,独占锁,默认值
     */
    private static Lock lock = new ReentrantLock(true);
    private static boolean aBoolean = true;

    @Override
    public void run() {
        Ticket ticket = new Ticket();
        //ticket.useLock();
        ticket.syncCodePiece();//单线程调用,其他线程进入阻塞
        //syncMethod();// 静态调用,锁同一个实例化的 Ticket对象
    }

    private void useLock(){
        while(aBoolean){
            // 加锁
            lock.lock();
            try{
                saleOfTickets();
            } finally {
                // 放锁
                lock.unlock();
            }
        }
    }

    /**
     * synchronized :可重入,不可中断,非公平
     */
    private synchronized void syncMethod() {
        while (aBoolean) {
            saleOfTickets();
        }
    }

    private void syncCodePiece(){
        while(aBoolean) {
            synchronized (obj) {
                saleOfTickets();
            }
        }
    }
    public void saleOfTickets(){
        //判断是否有票,ticketNum>0
        if(ticketNum > 0){
            //有票,让线程睡眠100毫秒
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //打印当前售出的票数字和线程名,票数减一
            String name = Thread.currentThread().getName();
            System.out.println("线程"+name+"销售电影票："+ticketNum--);
        }else {
            aBoolean = false;
        }
    }
}