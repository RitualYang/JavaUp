package com.wty;

import java.util.Scanner;

/**
 * 打印实体菱形
 *
 * @author wty
 * @date 2021/3/9 10:28
 */
public class PrintTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean b = true;
        do {
            try {
                System.out.print("输入*的数量(退出系统输入0)：");
                int i = scanner.nextInt();
                if (0 != i){
                    //打印菱形方法
                    int count = count(i);
                    System.out.println("剩余*:" + count);
                }else {
                    b = false;
                }
            }catch (RuntimeException e){
                System.out.println("请输入符合标准的类型");
            }


        }while (b);
        System.out.println("系统已推出");
    }

    private static int count(int i){
        int size = 0;
        int key = 1;
        boolean b = true;
        while (b){
            if (i >= key){
                size++;
                i = i - key;
                key = size * 4;
            }else {
                b = false;
            }
        }
        print(size);
        return i;
    }

    private static void print(int size){
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size - i; j++) {
                System.out.print(" ");
            }

            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print('*');
            }
            System.out.println();
        }
        for (int i = 1; i <= size-1; i++) {
            for (int j = 1; j <= i; j++){
                System.out.print(" ");
            }

            for (int k = 2*size-3; k >= 2 * i - 1; k--){
                System.out.print('*');
            }
            System.out.println();
        }

    }
}
