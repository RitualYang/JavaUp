package com.wty.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * TODO
 *
 * @author wty
 * @date 2020/9/25 16:05
 */
public class PasePdf {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String fileName = "tableAndTitle.pdf";
        PasePdf.test(fileName, 4);
    }

    private static void test(String fileName, Integer total) {
        Document document = new Document();
        document.setPageSize(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            PdfPTable table = new PdfPTable(total);
            for (int i = 0; i < total; i++) {
                PdfPTable row = createRow(1);
                table.addCell(row);
            }
            document.add(table);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static PdfPTable createRow(Integer total) throws DocumentException {
        // 创建一个有N列的表格
        PdfPTable table = new PdfPTable(total * 2);
        // TODO 设置上表样式
        table.setSpacingAfter(20);
        table.setTotalWidth(100);
        table.setLockedWidth(true);
        PdfPCell cell;
        // 列
        for (int i = 0; i < 8; i++) {
            // 行
            for (int j = 0; j < total; j++) {
                cell = new PdfPCell(new Phrase("it.next(), textFont"));
                cell.setMinimumHeight(30);
                cell.setUseAscender(true);
                cell.setColspan(2);
                if (i == 0) {
                    cell.setBackgroundColor(new BaseColor(231, 230, 230));
                }
                if (i == 4) {
                    cell.setColspan(1);
                    cell.setRowspan(2);
                }
                if (i == 5 || i == 6) {
                    cell.setColspan(1);
                    cell.setRowspan(1);
                }
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
            }
        }
        return table;
    }
}
