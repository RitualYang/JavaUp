package com.base.timeout;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * TODO
 *
 * @author wty
 * @date 2021/5/6 18:39
 */
public class TimeTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String nowDate = DateFormatUtils.format(new Date(), "yyyy-MM");
        long end = System.currentTimeMillis();
        String s = DateTime.now().toString("yyyy-MM");
        long lastEnd = System.currentTimeMillis();
        System.out.println("DateFormatUtils " + nowDate + " times:" + (end - start));
        System.out.println("DateTime " + s + " times:" + (lastEnd - end));
    }
}
