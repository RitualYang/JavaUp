package com.wty;

import io.reactivex.rxjava3.core.Flowable;

/**
 * @link https://github.com/ReactiveX/RxJava
 *
 * @author wty
 * @date 2021/8/9 20:39
 */
public class RxJavaTest {
    public static void main(String[] args) {
        Flowable.just("Hello world！").subscribe(System.out::println);
    }
}
