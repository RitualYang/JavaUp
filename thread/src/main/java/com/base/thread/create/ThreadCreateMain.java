package com.base.thread.create;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author WTY
 * @Date 2020/5/7 22:57
 */
public class ThreadCreateMain {
    public static void main(String[] args) {
        ThreadCreateMain threadCreateMain = new ThreadCreateMain();
        threadCreateMain.ExtendsThread();
        threadCreateMain.ImplementsRunnable();
        threadCreateMain.ImplementsCallable();
        //threadCreateMain.CreateExeutor();
        for (int i = 0; i<10; i++){
            System.out.println(Thread.currentThread().getName() + " 执行了：" + System.currentTimeMillis()  + "执行次数：" + i);
        }
    }

    /**
     * 通过继承Thread类,重写run方法
     */
    public void ExtendsThread(){
        MyThread myThread = new MyThread("MyThread");
        myThread.start();
    }

    /**
     * 通过实现Runnable接口,实现run方法,
     * Thread类启动
     */
    public void ImplementsRunnable(){
        Thread thread = new Thread(new MyRunnable(),"MyRunnable");

        // 可通过匿名内部类实现
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " 执行了：" + System.currentTimeMillis() + "执行次数：" + i);
                }
            }
        },"MyRunnable");*/
        thread.start();
    }

    /**
     * 通过实现Callable接口,重写call方法(具有返回值)
     * FutureTask装载该实现类
     * Thread启动
     */
    public void ImplementsCallable(){
        FutureTask<String> stringFutureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(stringFutureTask,"MyCallable");
        thread.start();
        // 需要先执行线程后,才能获取执行结果。
        try {
            String s = stringFutureTask.get();
            System.out.println("返回结果为：" + s + "执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void CreateExeutor(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new MyRunnable());
    }
}
