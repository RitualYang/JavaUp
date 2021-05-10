package com.wty;

import java.text.DecimalFormat;

/**
 * TODO
 *
 * @author wty
 * @date 2020/11/24 09:28
 */
public class StringTest {
    public static void main(String[] args) {
        //stringConstant();
        //spilt();
        System.out.println("我是好人 \n 真的吗");
    }

    public static void spilt(){
        String str = "userId;|;amount;|;";
        String[] split = str.split(";\\|;");
        for (String s : split){
            System.out.println(s);
        }
        System.out.println(split[2]);
    }

    public static void stringConstant(){
        String str1 = new StringBuilder("58").append("tongcheng").toString();
        /*System.out.println(str1);
        System.out.println(str1.intern());*/
        System.out.println(str1 == str1.intern());
        String str2 = new StringBuilder("ja").append("va").toString();
        /*System.out.println(str2);
        System.out.println(str2.intern());*/
        System.out.println(str2 == str2.intern());
        System.out.println(str2 == "java");
        str2 = str2.intern();
        System.out.println(str2 == "java");
        String str = new DecimalFormat("00000").format(111111);
        System.out.println(str);

    }
}
