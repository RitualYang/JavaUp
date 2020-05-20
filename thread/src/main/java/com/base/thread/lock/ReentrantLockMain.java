package com.base.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author WTY
 */
public class ReentrantLockMain {
    final static int FOR = 10;
    static ReentrantLock lock = new ReentrantLock();
    @SuppressWarnings("all")
    public static void main(String[] args){
        for(int i=0; i<FOR; i++){
            lock.lock();
            System.out.println("加锁次数："+(i+1));
        }
        for(int i=0; i<FOR; i++){
            try{
                System.out.println("解锁次数："+(i+1));
            } finally {
                lock.unlock();
            }
        }
    }
}
