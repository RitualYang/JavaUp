package com.wty.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * TODO
 *
 * @author wty
 * @Date 2020/9/26 13:17
 */
public class DemoPDFOut {
    public static void main(String[] args) {

        Document document = new Document(PageSize.A4);
        try {
            OutputStream out = new FileOutputStream("F:/pdf/pdfText.pdf");
            PdfWriter.getInstance(document, out);
            document.open();
            PdfPTable pdfPTable = new PdfPTable(3);
            for (int i = 0; i < 3; i++) {
                PdfPTable pdfPTable1 = new PdfPTable(2);
                getOneCalum(pdfPTable1);
                pdfPTable.addCell(pdfPTable1);
            }
            document.add(pdfPTable);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void getOneCalum(PdfPTable pdfPTable1) throws IOException, DocumentException {

        // 设置全宗号
        pdfPTable1.addCell(createCommonCell("全宗号", "0901"));
        // 设置类目
        pdfPTable1.addCell(createCommonCell("类目", "文书档案"));
        // 设置其他
        pdfPTable1.addCell(createCommonCell("我是", "好人"));
        pdfPTable1.addCell(createCommonCell("我是", "好人"));
        pdfPTable1.addCell(createCommonCell("我是", "好人"));
        // 设置件数
        PdfPCell fileNum1 = PdfUtils.createCell(2, 1, 100);
        fileNum1.addElement(createCommonPar("件数", null));
        pdfPTable1.addCell(fileNum1);
        PdfPCell fileNum2 = PdfUtils.createCell(1, 1, 50);
        fileNum2.addElement(createCommonPar("起始号", null));
        pdfPTable1.addCell(fileNum2);
        PdfPCell fileNum3 = PdfUtils.createCell(1, 1, 50);
        fileNum3.addElement(createCommonPar("结束号", null));
        pdfPTable1.addCell(fileNum3);
        pdfPTable1.addCell(createCommonCell("我是", "好人"));

    }

    static PdfPCell createCommonCell(String key, String value) {
        PdfPCell cell = PdfUtils.createCell(1, 2, 60);
        cell.addElement(createCommonPar(key, value));
        return cell;
    }

    static Paragraph createCommonPar(String key, String value) {
        Paragraph paragraph = PdfUtils.createParagraph();
        paragraph.add(key);
        paragraph.add(Chunk.NEWLINE);
        if (value != null) {
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(value);
        }
        return paragraph;
    }
}
