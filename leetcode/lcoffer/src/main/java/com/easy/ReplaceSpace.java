package com.easy;

/**
 * 替换空格
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 示例 1：
 *  输入：s = "We are happy."
 *  输出："We%20are%20happy."
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 * @author wty
 * @create 2020-02-26 19:48
 */
public class ReplaceSpace {
    public static void main(String[] args) {
        String s = "We are happy.";
        //空间复杂度：O(n)     时间复杂度：O(n)
        String s1 = replaceSpace1(s);//执行用时：1ms,内存消耗：37.4MB
        String s2 = replaceSpace2(s);//执行用时：0ms,内存消耗：37.6MB
    }

    public static String replaceSpace1(String s) {
        StringBuilder res = new StringBuilder();
        for(Character c : s.toCharArray())
        {
            if(c == ' ') {
                res.append("%20");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
    public static String replaceSpace2(String s){
        int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        String newStr = new String(array, 0, size);
        return newStr;
    }
}
