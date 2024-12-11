package com.wty;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author peter
 * @date 2022/4/5 11:34
 */
public class TreeMapTest {
    public static TreeMap<Integer, Integer> initDistributionLevel() {
        TreeMap<Integer, Integer> levelHashMap = Maps.newTreeMap();
        for (int i = 1; i <= 10; i++) {
            levelHashMap.put(i, i * 10000 + Integer.valueOf((int) (Math.random() * 1000)));
        }
        return levelHashMap;
    }

    public static void main(String[] args) {
//        TreeMap<Integer, Integer> integerDistributionLevelTreeMap = initDistributionLevel();
//        ArrayList<Integer> distributionLevels = Lists.newArrayList(integerDistributionLevelTreeMap.values());
//        distributionLevels.forEach(System.out::println);
        System.out.println(Integer.valueOf("5").byteValue());
    }

}
