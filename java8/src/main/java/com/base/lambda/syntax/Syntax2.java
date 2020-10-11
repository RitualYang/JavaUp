package com.base.lambda.syntax;

import com.base.lambda.interfaces.*;

/**
 * lambda语法精简
 *
 * @author: WTY
 * @Date: 2020/4/23-22-43
 */
public class Syntax2 {
    public static void main(String[] args) {
        /**
         * 1. 参数
         * 由于在接口的抽象方法中,已经定义了参数的数量和类型,所以参数的类型可以省略。
         * 备注: 如果需要省略类型,则每一个参数类型都要省略。
         */
        //多参无返回
        LambdaNoneReturnMutipleParameter lambdaNoneReturnMutipleParameter = (str1, str2) -> {
            System.out.println(str1 + str2 + " print LambdaNoneReturnMutipleParameter");
        };
        lambdaNoneReturnMutipleParameter.print("多参", "无返回");
        /**
         * 2. 参数小括号:
         * 如果参数列表中,参数的个数只有一个,此时小括号可以省略
         */
        LambdaNoneReturnSingleParameter lambdaNoneReturnSingleParameter = str -> {
            System.out.println(str + " print LambdaNoneReturnSingleParameter");
        };
        lambdaNoneReturnSingleParameter.print("单参无返回");
        /**
         * 3. 方法大括号:
         * 如果方法体中只有一条语句时,此时大括号可以省略
         */
        LambdaNoneReturnSingleParameter lambdaNoneReturnSingleParameter1 = str -> System.out.println(str + " print " +
                "LambdaNoneReturnSingleParameter");
        lambdaNoneReturnSingleParameter1.print("单参无返回");
        /**
         * 4. 方法返回:
         * 如果方法体中唯一的一条语句是一个返回语句,则在省略大括号的同时,也必须省略掉return关键字
         */
        LambdaSingleReturnNoneParameter lambdaSingleReturnNoneParameter = () -> "print LambdaSingleReturnNoneParameter";
    }
}
