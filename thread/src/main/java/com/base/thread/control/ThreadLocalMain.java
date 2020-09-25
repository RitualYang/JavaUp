package com.base.thread.control;

/**
 * @author WTY
 * @Date 2020/5/9 15:06
 */
public class ThreadLocalMain {

    //使用两个对象模拟转账
    public static void main(String[] args){
        Bank bank = new Bank();
        Transfer transfer = new Transfer(bank);
        Thread thread1 = new Thread(transfer, "客户1");
        Thread thread2 = new Thread(transfer, "客户2");

        thread1.start();
        thread2.start();
    }
    //创建银行对象：钱,取款,存款
    static class Bank {
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue(){
                System.out.println("线程创建,新建threadlocal的值");
                return 0;
            }
        };

        public Integer get(){
            return threadLocal.get();
        }

        public void set(Integer money){
            threadLocal.set(threadLocal.get() + money);
        }
    }

    /**
     * 创建转账对象：从银行中取钱,转账,保存到帐户
     */
    static class Transfer implements Runnable{
        private Bank bank;

        public Transfer(Bank bank){
            this.bank = bank;
        }

        @Override
        public void run() {
            for(int i=0; i<10; i++){
                bank.set(10);
                System.out.println(Thread.currentThread().getName()+"帐户余额："+bank.get());
            }
        }
    }
}
