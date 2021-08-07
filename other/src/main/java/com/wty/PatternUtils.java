package com.wty;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 常用正则检验工具
 * @link: https://www.runoob.com/java/java-regular-expressions.html
 * @author wty
 * @date 2021/8/5 11:44
 */
public class PatternUtils<aspect> {

    public final static Pattern dutyParagraphCompile;
    public final static Pattern mobileCompile;
    public final static Pattern emailCompile;
    static {
        dutyParagraphCompile = Pattern.compile("[0-9A-HJ-NPQRTUWXY]{2}\\d{6}[0-9A-HJ-NPQRTUWXY]{10}");
        mobileCompile = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$");
        emailCompile = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
    }

    public static boolean checkDutyParagraph(String dutyParagraph){
        return dutyParagraphCompile.matcher(StringUtils.defaultString(dutyParagraph)).matches();
    }
    public static boolean checkMobile(String mobile) {
        return mobileCompile.matcher(StringUtils.defaultString(mobile)).matches();
    }
    public static boolean checkEmail(String email) {
        return emailCompile.matcher(StringUtils.defaultString(email)).matches();
    }

    public static void main(String[] args) {
        System.out.println(checkDutyParagraph(null));
        System.out.println(checkMobile(""));
        System.out.println(checkEmail(""));
    }

}
