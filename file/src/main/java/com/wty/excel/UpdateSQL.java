package com.wty.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.FileUtils;

import java.io.*;
import java.util.List;

/**
 * @author wty
 * @date 2022/1/10 16:27
 * 描述:
 */
public class UpdateSQL {

    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Users\\Administrator\\Desktop\\整理数据.xlsx";
        DemoDataListener demoDataListener = new DemoDataListener();
        EasyExcel.read(new FileInputStream(fileName), DemoData.class, demoDataListener).headRowNumber(0).doReadAll();
        List<DemoData> list = demoDataListener.get();
        String format = "%s%s";
        File file = new File("C:\\Users\\Administrator\\Desktop\\in.sql");
        FileWriter fileWriter = new FileWriter(file);
        for (DemoData  demoData: list) {
            fileWriter.write(String.format(format, demoData.getUserId(), demoData.getStoreId()) + "\t\n");
        }
        // 关闭输出流
        fileWriter.close();
        fileWriter.close();
        System.out.println(demoDataListener);
    }
}
