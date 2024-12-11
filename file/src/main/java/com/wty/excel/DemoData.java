package com.wty.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author wty
 * @date 2022/1/10 16:29
 * 描述:
 */
@Data
public class DemoData {
    @ExcelProperty(index = 0)
    private String userId;
    @ExcelProperty(index = 1)
    private String userNickName;
    @ExcelProperty(index = 2)
    private String teamId;
    @ExcelProperty(index = 3)
    private String teamName;
    @ExcelProperty(index = 4)
    private String tradeVolumeEv;
    @ExcelProperty(index = 5)
    private String pnlEv;

}
