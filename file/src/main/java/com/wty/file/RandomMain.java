package com.wty.file;

import java.util.Random;

/**
 * @author peter
 * @date 2022/12/16 16:38
 */
public class RandomMain {

    public static void main(String[] args) {
        final Random tradeRan = new Random();
        final Random pnlRan = new Random();
        for (int i = 0; i< 1000;i++) {
            System.out.println(tradeRan.nextInt(10000000) + 5000000 + ","+ (pnlRan.nextInt(1020000) -1000000));
        }
    }

}
