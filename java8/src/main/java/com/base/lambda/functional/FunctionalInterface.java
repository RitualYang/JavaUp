package com.base.lambda.functional;

/**
 * @author wty
 * @date 2020/4/24 17:45
 */
public class FunctionalInterface {
    public static void main(String[] args) {
        //系统内置的一些函数式接口

        //Predicate<T>      :   参数T     返回值boolean
        //IntPredicate      :   参数int   返回值boolean
        //LongPredicate     :   参数long   返回值boolean
        //DoublePredicate   :   参数long   返回值boolean

        //Consumer<T>       :   参数T     返回值void
        //IntConsumer   :   参数int     返回值void
        //LongConsumer  :   参数long     返回值void
        //DoubleConsumer:   参数Double     返回值void

        //Function<T,R>     :   参数T,R  返回值是R
        //IntFunction<R>:    参数int   返回值是R
        //LongFunction<R>:    参数long   返回值是R
        //DoubleFunction<R>:    参数Double   返回值是R
        //IntToLongFunction...
        //IntToDoubleFunction...
        //...
        //Supplier<T>       :   参数无    返回值T
        //UnaryOperator<T>  :   参数T     返回值T
        //BinaryOperator<T> :   参数T,T  返回值T
        //BiFunctio<T,U,R>  :   参数T,U  返回值R
        //BiPredicate<T,U>  :   参数T,U  返回值boolean
        //BiConsumer<T,U>   :   参数T,U  返回值void
    }
}
