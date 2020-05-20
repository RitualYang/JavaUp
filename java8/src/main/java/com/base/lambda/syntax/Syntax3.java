package com.base.lambda.syntax;

import com.base.lambda.interfaces.LambdaSingleReturnSingleParameter;

/**
 * @author WTY
 * @Date 2020/4/23-22-43
 */
public class Syntax3 {
    public static void main(String[] args) {
        /**
         * 方法引用:
         * 可以快速的将一个Lambda表达式的实现指向一个已经实现的方法。
         * 语法:方法的隶属者::方法名
         * 注意:
         *  1.参数数量和类型一定要和接口中定义的方法保持一致。
         *  2.返回值的类型一定要和接口中定义的方法一致。
         */
        LambdaSingleReturnSingleParameter lambdaSingleReturnSingleParameter = str -> change(str);
        LambdaSingleReturnSingleParameter lambdaSingleReturnSingleParameter1 = Syntax3::change;
    }
    private static String change(String str) {
        return str + " print";
    }
}
