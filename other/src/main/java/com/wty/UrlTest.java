package com.wty;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Map;

/**
 * TODO
 *
 * @author wty
 * @date 2021/5/12 10:43
 */
public class UrlTest {
    public static void main(String[] args) {
        //String itemUrl = "https://pre.caibeike.net/h5/product/detail?dealCode=479589939748&strategyType=groupon&trace=cbk";
        String itemUrl = "https://pre.caibeike.net/ts/shopcard/item/5f916a72e4b0279b9d22c4db_5f916a729499ac539e084d04?trace=cbk";
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
        club();
        item();
    }

    private static void club() {
        String itemUrl = "https://pre.caibeike.net/ms/items/eventdetail/kid_601cfe53e4b08b14f55e0da8";
        String itemCode = itemUrl.substring(itemUrl.lastIndexOf("_") + 1);
        System.out.println(itemCode);
    }

    public static void item() {
        String itemUrl = "https://pre.caibeike.net/ms/items/eventdetail/kid_601cfe53e4b08b14f55e0da8_null";
        String[] s = itemUrl.split("_");
        if (s.length >= 2) {
            System.out.println(s[1]);
        }
    }
    public static void pass() {
        String itemUrl = "";
        String passcardId = itemUrl.substring(itemUrl.lastIndexOf("/") + 1, itemUrl.indexOf("_") + 1);

    }
}
