package com.wty.test.benchmark;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author peter
 * @date 2022/11/14 17:31
 */
public class CouMain {

    public static void main(String[] args) {
        final ArrayList<String> strings = Lists.newArrayList(
                "Qatar", "Ecuador", "Senegal", "Netherlands",
                "England","Iran","USA", "Wales",
                "Argentina","Saudi Arabia","Mexico","Poland",
                "France","Australia","Denmark","Tunisia",
                "Spain","Costa Rica","Germany","Japan",
                "Belgium","Canada","Morocco","Croatia",
                "Brazil","Serbia","Switzerland","Cameroon",
                "Portugal","Ghana","Uruguay","South Korea"
                );
        strings.stream().sorted().forEach(str -> {
            System.out.println(str);
        });

    }
}
