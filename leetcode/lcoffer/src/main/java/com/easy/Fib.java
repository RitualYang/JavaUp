package com.easy;

/**
 * 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 * @author wty
 * @create 2020-02-27 21:05
 */
public class Fib {
    public static void main(String[] args) {
        for (int i = 0;i< 100;i++){
            //空间复杂度：O(1)     时间复杂度：O(n)
            int fib = fib(i);//执行用时：0ms,内存消耗：36.6MB
            System.out.println(fib);
        }
    }

    /**
     * 从第三个数起，每个数等于前两个数的和
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        long fibOne = 0;
        long fibTow = 1;
        long res = 0;
        for (int i = 2; i <= n; i++) {
            res = (fibOne + fibTow) % 1000000007;
            fibOne = fibTow;
            fibTow = res;
        }
        return (int)res;
    }
}
