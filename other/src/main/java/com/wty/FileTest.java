package com.wty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

/**
 * @author peter
 * @date 2022/8/11 17:54
 */
public class FileTest {

    public static void main(String[] args) throws JsonProcessingException, UnsupportedEncodingException {
        pushS3("test.json","{11111}");
//        json();
//        System.out.println(URLEncoder.encode("www.baidu.com/s", "UTF-8"));
//        System.out.println(encodeUrl("www.baidu.com/s?test=123213sjaco陈总洗剪吹"));
    }

    private static void json() throws JsonProcessingException {
        String json = "{\n"
                + "    \"webimg\": \"/pubimg/814f645627d9869cfa4c856526d0f062c16341a063226f4b949a261cf08da3f4.jpg\",\n"
                + "    \"webBannerAlt\": \"23213\",\n"
                + "    \"appLink\": \"https://phemex.com/ru/announcements/Путешествуй-с-phemex-выиграйте-роскошную-поездку-в-Дубай\",\n"
                + "    \"webLink\": \"https://phemex.com/ru/announcements/Путешествуй-с-phemex-выиграйте-роскошную-поездку-в-Дубай\",\n"
                + "    \"appimg\": \"/pubimg/814f645627d9869cfa4c856526d0f062c16341a063226f4b949a261cf08da3f4.jpg\",\n"
                + "    \"appLinkType\": \"web\",\n"
                + "    \"description\": \"http://www.baidu.com2343434\",\n"
                + "    \"location\": 1,\n"
                + "    \"lang\": \"en\",\n"
                + "    \"sceneType\": \"SPOT\",\n"
                + "    \"dataKey\": \"8888\"\n"
                + "}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> stringObjectMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(stringObjectMap);
    }

    private static void pushS3(String fileName, String body) {
        File file = null;
        try(BufferedWriter out = new BufferedWriter(new FileWriter(fileName)))  {
            out.write(body);

        } catch (Exception e) {
        } finally {
            if (file != null && file.exists() && !file.delete()) {
            }
        }
        file = new File(fileName);
        System.out.println(file);
        System.out.println(file.length());

    }

    private static String encodeUrl(String url) {
        String resultUrl = "";
        //遍历字符串
        for (int i = 0; i < url.length(); i++) {
            char charIndex = url.charAt(i);
            if (isChinese(charIndex)) {
                //如果是中文，则对中文转码
                try {
                    resultUrl += URLEncoder.encode(charIndex + "", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                resultUrl += charIndex;
            }
        }
        return resultUrl;
    }

    /**
     * 是否是中文字符
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

}
