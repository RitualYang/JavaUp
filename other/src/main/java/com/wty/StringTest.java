package com.wty;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author wty
 * @date 2020/11/24 09:28
 */
public class StringTest {
    public static void main(String[] args) {
        //stringConstant();
        //spilt();
        //System.out.println("我是好人 \n 真的吗");
//        String skuTitle = "入住时段：特殊时段入住(2021.06.17-2021.06.18、2021.06.19-2021.06.20、2021.06.21-2021.06.22、2021.06.23-2021.06.24)";
//        String skuTitle2 = "入住时段：周一、周二、周四、周五、周日(2021.04.29-2021.05.31)";
//        fillSkuUseOpeningTime(skuTitle);
//        System.out.println("------------");
//        fillSkuUseOpeningTime(skuTitle2);
//        String skuTitle3 = "入住时段：周一至周二、周四至周五、周日(2021.04.29-2021.05.31)";
//        fillSkuUseOpeningTime(skuTitle3);
        System.out.println(true || false && true && false);
    }

    public static void spilt(){
        String str = "userId;|;amount;|;";
        String[] split = str.split(";\\|;");
        for (String s : split){
            System.out.println(s);
        }
        System.out.println(split[2]);
    }

    public static void stringConstant(){
        String str1 = new StringBuilder("58").append("tongcheng").toString();
        /*System.out.println(str1);
        System.out.println(str1.intern());*/
        System.out.println(str1 == str1.intern());
        String str2 = new StringBuilder("ja").append("va").toString();
        /*System.out.println(str2);
        System.out.println(str2.intern());*/
        System.out.println(str2 == str2.intern());
        System.out.println(str2 == "java");
        str2 = str2.intern();
        System.out.println(str2 == "java");
        String str = new DecimalFormat("00000").format(111111);
        System.out.println(str);

    }


    private static void fillSkuUseOpeningTime(String skuTitle) {
        String[] split = {};
        if (skuTitle.contains(":")) {
            split = StringUtils.split(skuTitle, ":");
        } else if (skuTitle.contains("：")) {
            split = StringUtils.split(skuTitle, "：");
        }
        ArrayList<String> openingTimesDesc = Lists.newArrayList();
        if (split.length > 1) {
            //entity.setOpeningTimePrefix(String.format("%s：", split[0]));
            if (split[1].contains("(")) {
                String[] split1 = split[1].split("[(]");
                String[] timeTitles = StringUtils.split(split1[1], "、");
                openingTimesDesc.add(String.format("%s(%s", split1[0], timeTitles[0]));
                if (timeTitles.length > 1) {
                    for (int i = 1; i < timeTitles.length; i++) {
                        openingTimesDesc.add(timeTitles[i]);
                    }
                }

                openingTimesDesc.stream().forEach(System.out::println);
                return;
            }
            String[] timeTitles = StringUtils.split(split[1], "、");
            Arrays.stream(timeTitles).collect(Collectors.toList()).stream().forEach(System.out::println);
            return;
        }
    }
}
