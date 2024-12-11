package com.wty.test.benchmark;

import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author peter
 * @date 2022/11/3 11:27
 */
public class TestMasin {

    public static void main(String[] args) {

        List<BigDecimal> lists = Lists.newArrayList(
                new BigDecimal(8).multiply(new BigDecimal(1)),
                new BigDecimal(15).multiply(new BigDecimal(1)),
                new BigDecimal(25).multiply(new BigDecimal(1)),
                new BigDecimal(45).multiply(new BigDecimal(1)),
                new BigDecimal(60).multiply(new BigDecimal(1)),
                new BigDecimal(100).multiply(new BigDecimal(1)),
                new BigDecimal(200).multiply(new BigDecimal(1))
        );
        final ArrayList<Integer> integers = Lists.newArrayList(200, 500, 1000, 2000, 4000, 6000, 10000);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.print("{\\\"0\\\":[{\\\"competitionLevelEnum\\\":\\\"FIRST\\\",\\\"prizePoolEr\\\":0},{\\\"competitionLevelEnum\\\":\\\"SECOND\\\",\\\"prizePoolEr\\\":0},{\\\"competitionLevelEnum\\\":\\\"THIRD\\\",\\\"prizePoolEr\\\":0},{\\\"competitionLevelEnum\\\":\\\"NUM_4_10\\\",\\\"prizePoolEr\\\":0}],");
        lists.forEach(dec -> {
            final Integer integer = integers.get(atomicInteger.getAndIncrement());
            System.out.print("\\\""+ integer +"\\\":");
            System.out.print("[{\\\"competitionLevelEnum\\\":\\\"FIRST\\\",\\\"prizePoolEr\\\":" +  dec.multiply(new BigDecimal(22)).multiply(new BigDecimal(100000000)).stripTrailingZeros().toPlainString() + "},");
            System.out.print("{\\\"competitionLevelEnum\\\":\\\"SECOND\\\",\\\"prizePoolEr\\\":" + dec.multiply(new BigDecimal(15)).multiply(new BigDecimal(100000000)).stripTrailingZeros().toPlainString() + "},");
            System.out.print("{\\\"competitionLevelEnum\\\":\\\"THIRD\\\",\\\"prizePoolEr\\\":" +  dec.multiply(new BigDecimal(13)).multiply(new BigDecimal(100000000)).stripTrailingZeros().toPlainString() + "},");
            System.out.print("{\\\"competitionLevelEnum\\\":\\\"FOURTH\\\",\\\"prizePoolEr\\\":" + dec.multiply(new BigDecimal(11)).multiply(new BigDecimal(100000000)).stripTrailingZeros().toPlainString() + "},");
            System.out.print("{\\\"competitionLevelEnum\\\":\\\"FIFTH\\\",\\\"prizePoolEr\\\":" +  dec.multiply(new BigDecimal(10)).multiply(new BigDecimal(100000000)).stripTrailingZeros().toPlainString() + "},");
            System.out.print("{\\\"competitionLevelEnum\\\":\\\"SIXTH\\\",\\\"prizePoolEr\\\":" +  dec.multiply(new BigDecimal(9)).multiply(new BigDecimal(100000000)).stripTrailingZeros().toPlainString() + "},");
            System.out.print( "{\\\"competitionLevelEnum\\\":\\\"SEVENTH\\\",\\\"prizePoolEr\\\":" + dec.multiply(new BigDecimal(8)).multiply(new BigDecimal(100000000)).stripTrailingZeros().toPlainString() + "},");
            System.out.print("{\\\"competitionLevelEnum\\\":\\\"EIGHTH\\\",\\\"prizePoolEr\\\":" +  dec.multiply(new BigDecimal(5)).multiply(new BigDecimal(100000000)).stripTrailingZeros().toPlainString() + "},");
            System.out.print("{\\\"competitionLevelEnum\\\":\\\"NINTH\\\",\\\"prizePoolEr\\\":" +  dec.multiply(new BigDecimal(4)).multiply(new BigDecimal(100000000)).stripTrailingZeros().toPlainString() + "},");
            System.out.print("{\\\"competitionLevelEnum\\\":\\\"TENTH\\\",\\\"prizePoolEr\\\":" +  dec.multiply(new BigDecimal(3)).multiply(new BigDecimal(100000000)).stripTrailingZeros().toPlainString() + "}],");
                }
        );
        System.out.println("}");
    }

}
