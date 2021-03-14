package com.base.thread.local;

/**
 * TODO
 *
 * @author wty
 * @date 2021/3/1 20:34
 */
public class ThreadLocalMain {
    static InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal<>();
    static ThreadLocal threadLocal = new ThreadLocal();
    public static void main(String[] args) {
        inheritableThreadLocal.set("a");
        System.out.println(inheritableThreadLocal.get());
        inheritableThreadLocal.remove();
        threadLocal.set(Thread.currentThread().getName());
        String  str = (String) threadLocal.get();
        System.out.println(str);
        threadLocal.remove();
    }
}
