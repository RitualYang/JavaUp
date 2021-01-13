package com.wty.jvm;

/**
 * TODO
 *
 * @author wty
 * @date 2021/1/3 16:44
 */
public class Exec {
    public static void main(String[] args) {
        new children("12");
    }
}

class parent {
    private String name;
    public parent(){
        this.name = "parent";
        System.out.println("父类无参构造方法");
    }
}
class children extends parent{
    private String age;
    public children(String age){
        System.out.println("子类构造方法");
    }
}
