package com.base.stream.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author WTY
 * @Date 2020/4/26 22:00
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    /**
     * 临界值
     */
    private static final long THRESHOLD = 10000L;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork(); // 拆分子任务。同时压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
