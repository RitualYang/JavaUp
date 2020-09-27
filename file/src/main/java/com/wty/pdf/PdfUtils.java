package com.wty.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;

import java.io.IOException;

/**
 * pdf导出
 *
 * @author wty
 * @Date 2020/9/26 14:43
 */
public class PdfUtils {

    static Font CH_FONT = null;
    static {
        BaseFont baseFont = null;
        try {
            baseFont = BaseFont.createFont("font/STZHONGS.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CH_FONT = new Font(baseFont);
    }

    /**
     * 格子
     * @param row
     * @param col
     * @param height
     * @return
     */
    public static PdfPCell createCell(int row, int col, int height){
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setRowspan(row);
        pdfPCell.setColspan(col);
        pdfPCell.setMinimumHeight(height);
        pdfPCell.setHorizontalAlignment(1);
        return pdfPCell;
    }

    /**
     * 块
     * @return
     */
    public static Chunk createChunk(){
        return new Chunk();
    }

    /**
     * 段落
     * @return
     */
    public static Paragraph createParagraph(){
        Paragraph elements = new Paragraph("", CH_FONT);
        elements.setAlignment(1);
        return elements;
    }

}
