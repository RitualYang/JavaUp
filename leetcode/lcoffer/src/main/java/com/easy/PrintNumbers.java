package com.easy;

/**
 * 打印从1到最大的n位数
 *
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
 * 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 * @author wty
 * @create 2020-02-29 20:50
 */
public class PrintNumbers {
    public static void main(String[] args) {
        //空间复杂度：O(1)     时间复杂度：O(n)
        printNumbers1(4);//执行用时：1ms,内存消耗：48.6MB
    }
    public static int[] printNumbers(int n) {
        int max = (int)Math.pow(10, n);
        int[] ans = new int[max - 1];
        for(int i = 1 ; i <= max - 1 ; i++){
            ans[i - 1] = i;
        }
        return ans;
    }

    public static int[] printNumbers1(int n){
        if(n <= 0) {
            return new int[0];
        }
        int result = 1;
        int x = 10;
        //该题考查快速幂；
        while(n != 0){
            if((n & 1) == 1){
                result *= x;
            }
            n >>= 1;
            x *= x;
        }
        int len = result - 1;
        int[] array = new int[len];
        for(int i = 0;i < len;i++){
            array[i] = i + 1;
        }
        return array;
    }
}
