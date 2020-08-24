package com.easy;

/**
 * II. 青蛙跳台阶问题
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 * @author wty
 * @create 2020-02-28 21:03
 */
public class NumWays {

    public static void main(String[] args) {
        //空间复杂度：O(1)     时间复杂度：O(n)
        for (int i = 0;i< 100;i++) {
            int fib = numWays(i);//执行用时：0ms,内存消耗：36.4MB
            System.out.println(fib);
        }
    }
    public static int numWays(int n) {
        int a = 1, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
