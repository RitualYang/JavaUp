package com.wty.file;

import java.io.File;

/**
 * 递归删除文件
 *
 * @author wty
 * @date 2021/1/14 21:09
 */
public class DeFile {

    public static void main(String[] args) {
        String filePath = "F:\\BaiduNetdiskDownload\\极客时间\\专栏课";
        File file = new File(filePath);
        extracted(file);
    }

    /**
     * 递归删除
     * @param file
     */
    private static void extracted(File file) {
        if (file.isDirectory()){
            File[] newFile = getFile(file);
            for (File file1 : newFile) {
                extracted(file1);
            }
        }else {
            delete(file,".html");
        }
    }

    /**
     * 判断该文件是否含有字段
     * @param file
     * @param str
     */
    public static void delete(File file,String str){
        if (file.isFile()){
            if (file.getName().contains(str)){
                file.delete();
            }
        }

    }

    /**
     * 获取文件夹下所有文件
     * @param file
     * @return
     */
    private static File[] getFile(File file) {
        String[] fileName = file.list();
        File[] newFile = new File[fileName.length];
        for (int i = 0; i < fileName.length; i++) {
            newFile[i] = new File(file.getPath() +File.separator +fileName[i]);
        }
        return newFile;
    }
}
