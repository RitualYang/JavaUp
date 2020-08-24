package com.easy;

/**
 * 二进制中1的个数
 * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，
 * 把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数
 * 输出 2。
 *
 * 示例 1：
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 * @author wty
 * @create 2020-02-29 20:44
 */
public class HammingWeight {
    public static void main(String[] args) {
        //空间复杂度：O(log2n)     时间复杂度：O(1)
        int i = hammingWeight(9);//执行用时：1ms,内存消耗：36.3MB
        System.out.println(i);
    }
    /**
     * 运用与运算符
     * @param n
     * @return
     */
    public static int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res += n & 1;//判断最低位是否是1
            n >>>= 1;//位运算，向右移动一个位数
        }
        return res;
    }
}
