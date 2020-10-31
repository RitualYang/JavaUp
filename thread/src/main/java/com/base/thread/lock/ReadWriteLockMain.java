package com.base.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * otherThread(white)   otherThread(read)
 * firstThread(white)   f(T)o(F)             f(T)o(F)
 * firstThread(read)    f(T)o(F)             f(T)o(T)
 *
 * @author wty
 */
public class ReadWriteLockMain {
    /**
     * 操作的map对象
     */
    private final Map<String, String> map = new HashMap<>();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * 读操作锁
     */
    private final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    /**
     * 写操作锁
     */
    private final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        final ReadWriteLockMain readWriteLockMain = new ReadWriteLockMain();
        readWriteLockMain.put("key", "value");
        new Thread("读线程1") {
            @Override
            public void run() {
                System.out.println(readWriteLockMain.get("key"));
            }
        }.start();
        new Thread("读线程2") {
            @Override
            public void run() {
                System.out.println(readWriteLockMain.get("key"));
            }
        }.start();
        new Thread("读线程3") {
            @Override
            public void run() {
                System.out.println(readWriteLockMain.get("key"));
            }
        }.start();
    }

    public String get(String key) {
        readLock.lock();//读操作加锁
        try {
            System.out.println(Thread.currentThread().getName() + "读操作已加锁,开始读操作...");
            Thread.sleep(3000);
            return map.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println(Thread.currentThread().getName() + "读操作已解锁,读操作结束...");
            readLock.unlock();
        }
    }

    public void put(String key, String value) {
        writeLock.lock();//写操作加锁
        try {
            System.out.println(Thread.currentThread().getName() + "写操作已加锁,正在执行写操作");
            Thread.sleep(3000);
            map.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "写操作解锁,写操作执行完成！");
            writeLock.unlock();
        }
    }
}
