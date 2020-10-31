package com.base.lambda.syntax;

import com.base.lambda.interfaces.*;

/**
 * @author: wty
 * @date: 2020/4/23-22-43
 */
public class Syntax1 {
    public static void main(String[] args) {
        // 1. Lambda表达式的基础语法:
        // Lambda是一个匿名函数
        // 参数列表 方法体

        // () : 用来描述参数列表
        // {} :用来描述方法体
        // -> : Lambda运算符,读作goes to

        // 无参无返回
        LambdaNoneReturnNoneParameter lambdaNoneReturnNoneParameter = () -> {
            System.out.println("无参无返回 print lambdaNoneReturnNoneParameter");
        };
        lambdaNoneReturnNoneParameter.print();
        //单参无返回
        LambdaNoneReturnSingleParameter lambdaNoneReturnSingleParameter = (String str) -> {
            System.out.println(str + "print LambdaNoneReturnSingleParameter");
        };
        lambdaNoneReturnSingleParameter.print("单参无返回 ");
        //多参无返回
        LambdaNoneReturnMutipleParameter lambdaNoneReturnMutipleParameter = (String str1, String str2) -> {
            System.out.println(str1 + str2 + " print LambdaNoneReturnMutipleParameter");
        };
        lambdaNoneReturnMutipleParameter.print("多参", "无返回");
        //无参有返回
        LambdaSingleReturnNoneParameter lambdaSingleReturnNoneParameter = () -> {
            return "无参有返回 print lambdaSingleReturnNoneParameter";
        };
        System.out.println(lambdaSingleReturnNoneParameter.print());
        //单参有返回
        LambdaSingleReturnSingleParameter lambdaSingleReturnSingleParameter = (String str) -> {
            return str + " print LambdaSingleReturnSingleParameter";
        };
        System.out.println(lambdaSingleReturnSingleParameter.print("单参有返回"));
        //多参有返回
        LambdaSingleReturnMutipleParameter lambdaSingleReturnMutipleParameter = (String str1, String str2) -> {
            return str1 + str2 + " print LambdaSingleReturnMutipleParameter";
        };
        System.out.println(lambdaSingleReturnMutipleParameter.print("多参", "有返回"));
    }
}
