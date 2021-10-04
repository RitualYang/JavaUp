package com.wty;

import io.reactivex.rxjava3.functions.Action;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * TODO
 *
 * @author wty
 * @date 2021/4/13 18:09
 */
public class DateTimeTest {
    public static void main(String[] args) throws Throwable {
        //Date date = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
        //System.out.println(sdf.format(date));
        //List<String> strings = Arrays.asList("2022-02-30", "2022-01-30", "2022-03-30", "2022-01-10", "2021-01-30");
        //System.out.println(strings.stream().sorted().findFirst());
        //System.out.println(getLiveRecordEndTime("2020.03.10 14:40"));
        //System.out.println(fastDateFormat.format(DateTime.parse("2021-06-11").plusDays(DAYS).toDate()));
        timeLogInfo(() -> forAction(() -> calendarDate(12)));
        timeLogInfo(() -> forAction(() -> dateTime(12)));
    }

    private static void timeLogInfo(Action action) throws Throwable {
        long startTime = System.currentTimeMillis();
        action.run();
        System.out.println("执行时间:" + (System.currentTimeMillis() - startTime));
    }

    private static void forAction(Action action) throws Throwable {
        int i = 100;
        while (i-- > 0) {
            action.run();
            System.out.println("--");
        }
    }

    private static void dateTime(Integer times) {
        DateTime.now().plusMonths(times).toDate();
    }

    private static void calendarDate(Integer times) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MINUTE, times);
        instance.getTime();
    }

}
