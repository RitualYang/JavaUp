package com.base.thread.inform;

import java.util.concurrent.Semaphore;

/**
 * Semaphore用于控制对某组资源的访问权限。
 *
 * @author WTY
 */
public class SemaphoreMain {

    public static void main(String[] args) {
        //代表工人数8个
        int workers = 8;
        //代表机器数3个
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < workers; i++) {
            new Thread(new Work(semaphore)).start();
        }
    }

    static class Work implements Runnable {

        //机器数
        private Semaphore semaphore;

        public Work(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                //1.工人要去获取机器
                semaphore.acquire();
                //2.打印工人获取到机器,开始工作
                String name = Thread.currentThread().getName();
                System.out.println(name + "获取到机器,开始工作。。。");
                //3.线程睡眠1000毫秒,模拟工人使用机器工作过程
                Thread.sleep(1000);
                //4.使用完毕,释放机器,打印工人使用完毕,释放机器
                System.out.println(name + "使用完毕,释放机器！");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}