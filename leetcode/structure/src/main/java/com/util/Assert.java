package com.util;

/**
 * @author WTY
 * @Date 2020/6/14 21:55
 */
public class Assert {
    public static void test(boolean value){
        try {
            if (!value) {
                throw new Exception("测试未通过");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
