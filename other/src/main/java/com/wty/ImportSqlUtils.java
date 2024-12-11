package com.wty;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author peter
 * @date 2022/6/14 22:32
 */
public class ImportSqlUtils {

    private static final String COMMA = ",";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String DATE_FORMAT_STR = "yyyy/MM/dd";
    private static final String SEMICOLON = ";";

    private static  final Set<String> countrys = Sets.newHashSet("IND","VEN","IDN","BGD","VNM","PHL", "PAK");


    private static final String sql = "INSERT INTO phemex_activity.t_referral_activity_user (user_id, referral_id, status, reward_json, activity_date, country) VALUES (%s, %s, 0, '[]', '202206', '%s');";
    private ImportSqlUtils() {
    }

    public static void main(String[] args) {
        readResourcesTask("referral-activity-user-info.csv");
    }

    public static void readResourcesTask(String fileName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (Objects.isNull(inputStream)) {
            return;
        }
        Map<String, String> map = Maps.newHashMap();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] item = line.split(COMMA);
                map.put(item[0],String.format(sql, item[0], item[1], item[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ddd:" + map.size());
        map.values().stream().forEach(System.out::println);
    }

}
