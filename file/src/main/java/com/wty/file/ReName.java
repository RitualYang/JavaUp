package com.wty.file;

import java.io.File;

/**
 * TODO
 * 对文件夹下文件进行重命名
 * @author wty
 * @date 2020/11/8 10:04
 */
public class ReName {
    public static void main(String[] args) {
        String filePath = "F:\\BaiduNetdiskDownload\\进阶\\尚硅谷Java学科全套教程\\1.尚硅谷全套JAVA教程--基础阶段\\尚硅谷 韩顺平 图解Java设计模式\\视频";
        String world = "-尚硅谷-图解Java设计模式";
        ReNameFile(filePath,world);

    }

    public static void ReNameFile(String filePath, String world) {
        File file = new File(filePath);
        String[] fileName = file.list();
        File[] newFile = new File[fileName.length];
        for (int i = 0; i < fileName.length; i++) {
            newFile[i] = new File(filePath +File.separator +fileName[i]);
        }
        for (File file1 : newFile) {
            String newFilePath =  filePath +File.separator+ file1.getName().replaceAll(world, "");
            boolean b = file1.renameTo(new File(newFilePath));
            if (b){
                System.out.println(file1.getName() + " 已重命名");
            }else {
                System.out.println(file1.getName() + "重命名失败");
            }
        }
    }
}
