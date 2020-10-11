package com.base.thread.volat;

/**
 * @author WTY
 * @Date 2020/5/9 16:38
 */
public class Singleton {
    private volatile static Singleton instance = null;

    private Singleton() {
        System.out.println("初始化");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //getErrorInstance();
                    getInstance();
                }
            }).start();
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                /**
                 * 与volatile关键词连用
                 * 判断实例化是否被创建,如果已经被创建则跳过初始化
                 */
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static Singleton getErrorInstance() {
        if (instance == null) {
            /**
             * 线程1,2,3判断完成后,执行到这里
             * 线程1获取锁继续执行,2、3等待。
             */
            synchronized (Singleton.class) {
                // 线程1实例化完成后,释放锁,线程2再次实例(线程3重复操作)
                instance = new Singleton();
            }
        }
        return instance;
    }
}
