package com.base.timeout;

import org.junit.Test;

import java.time.*;

/**
 * @author WTY
 * @Date 2020/4/27 17:19
 */
public class LocalDateTimeMain {
    /**
     * LocalDate
     * LocalTime
     * LocalDateTime
     */
    @Test
    public void test1() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2020, 3, 3, 12, 12);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);
        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
    }

    /**
     * Instant: 时间戳（以Unix 元年:1970年1月1日00:00:00 到某一时刻之间的毫秒值）
     */
    @Test
    public void test2() {
        Instant now = Instant.now(); // 默认获取UTC时区
        System.out.println(now);
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(now.toEpochMilli());
        Instant instant = Instant.ofEpochSecond(1000);
        System.out.println(instant);
    }

    /**
     * Duration : 计算两个“时间”之间的间隔。
     * Period   : 计算两个“日期”之间的间隔。
     */
    @Test
    public void test3() {
        Instant now = Instant.now();
        try {
            Thread.sleep(1234);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant now1 = Instant.now();
        Duration duration = Duration.between(now, now1);
        System.out.println(duration.getSeconds());
        System.out.println("----------------------------------");
        LocalDate ld1 = LocalDate.of(2020, 1, 1);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period.getYears());
        System.out.println(period.getDays());
        System.out.println(period.getMonths());
    }
}
