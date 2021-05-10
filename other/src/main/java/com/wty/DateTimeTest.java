package com.wty;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author wty
 * @date 2021/4/13 18:09
 */
public class DateTimeTest {
    public static void main(String[] args) {
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
//        System.out.println(sdf.format(date));
        List<String> strings = Arrays.asList("2022-02-30", "2022-01-30", "2022-03-30", "2022-01-10", "2021-01-30");
        System.out.println(strings.stream().sorted().findFirst());

    }

}
