package com.wty;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author wty
 * @date 2021/5/12 10:43
 */
public class UrlTest {
    public static void main(String[] args) {
        String itemUrl = "https://www.baidu.com/xxx?wod=adas";
        String[] urlParts = itemUrl.split("\\?");
        // 入参不完整
        if (urlParts.length <= 1) {
            return;
        }
        Map<String, String> paramsMap = Maps.newHashMap();
        ArrayList<String> pathList = Lists.newArrayList();
        String[] paramsList = urlParts[1].split("&");
        for (String param : paramsList) {
            String[] keyValue = param.split("=");
            paramsMap.put(keyValue[0], keyValue[1]);
        }
        String[] split = urlParts[0].split("/");
        for (String s : split) {
            if (!s.isEmpty() && !"https:".equals(s) && !"http:".equals(s)) {
                pathList.add(s);
            }
        }
        System.out.println(itemUrl.indexOf("?") + " " + itemUrl.lastIndexOf("/"));
        System.out.println(itemUrl.substring(itemUrl.lastIndexOf("/") + 1, itemUrl.indexOf("?")));
        System.out.println(JSON.toJSONString(pathList));
        System.out.println(JSON.toJSONString(paramsMap));
    }
}
