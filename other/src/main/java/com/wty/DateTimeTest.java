package com.wty;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author wty
 * @date 2021/4/13 18:09
 */
public class DateTimeTest {
    public static void main(String[] args) throws ParseException {
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
//        System.out.println(sdf.format(date));
//        List<String> strings = Arrays.asList("2022-02-30", "2022-01-30", "2022-03-30", "2022-01-10", "2021-01-30");
//        System.out.println(strings.stream().sorted().findFirst());
//        System.out.println(getLiveRecordEndTime("2020.03.10 14:40"));
        System.out.println(fastDateFormat.format(DateTime.parse("2021-06-11").plusDays(DAYS).toDate()));

        //adate();
//        ArrayList<String> objects = Lists.newArrayList();
//        List<String> hotelAuidtSkuDateInfo = getHotelAuidtSkuDateInfo(objects);
//        for (int i = 0; i < hotelAuidtSkuDateInfo.size(); i++) {
//            System.out.println(hotelAuidtSkuDateInfo.get(i).toString());
//        }
    }

    private static Date getLiveRecordEndTime(String data) throws ParseException {
        String duration = "3 min";
        Date startTime = DateUtils.parseDate(data, "yyyy.MM.dd HH:mm");
        if (StringUtils.isNotBlank(duration)) {
            if (duration.contains(" min")) {
                Integer time = Integer.valueOf(duration.substring(0, duration.indexOf(" min")));
                Calendar newTime = Calendar.getInstance();
                newTime.setTime(startTime);
                newTime.add(Calendar.MINUTE, time);
                return newTime.getTime();
            }
        }
        return startTime;
    }

    private static final int DAYS = 720;
    private static FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd");

    public static void adate() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        String endDate = fastDateFormat.format(calendar.getTime());

        if (endDate.compareTo("2031-06-23") < 0) {
            endDate = "2031-06-23";
        }
        // 接待完为止生成默认库存 720天
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-23"));
        cal.add(Calendar.DATE, DAYS);
        endDate = fastDateFormat.format(cal.getTime());



        calendar.add(Calendar.DATE, 0);
        String startTime = fastDateFormat.format(calendar);
        calendar.add(Calendar.DATE, DAYS);
        String endTime = fastDateFormat.format(calendar.getTime());
        if (endDate.compareTo(endTime) < 0) {
            endTime = endDate;
        }
        System.out.println(String.format("startTime :%s, endTime: %s",startTime, endTime));
    }

    private static List<String> getHotelAuidtSkuDateInfo(List<String> strings) {
        strings.add("2021年10月31日");
        strings.add("2021年06月21日");
        strings.add("2021年07月31日");
        return strings.stream().map(date -> String.format("%s", date))
                .sorted().collect(Collectors.toList());
    }

}
