package com.wty.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;


public class Test {

    Document document = null;// 建立一个Document对象      
    private static Font headFont ;
    private static Font keyFont ;
    private static Font nbspFont;
    private static Font textfont_H ;
    private static Font textfont_B ;
    private static Font textfont_13;
    private static Font textfont_12;
    private static Font textfont_11;
    private static Font textfont_10;
    private static Font textfont_9;
    private static Font textfont_8;
    private static Font textfont_7;
    private static Font textfont_6;
    int maxWidth = 520;


    static{    
        BaseFont bfChinese_H;
        try {    
            /** 
             * 新建一个字体,iText的方法 STSongStd-Light 是字体，在iTextAsian.jar 中以property为后缀 
             * UniGB-UCS2-H 是编码，在iTextAsian.jar 中以cmap为后缀 H 代表文字版式是 横版， 相应的 V 代表竖版  
             */
            bfChinese_H = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);    

            headFont = new Font(bfChinese_H, 20, Font.NORMAL);
            keyFont = new Font(bfChinese_H, 26, Font.BOLD); 
            nbspFont = new Font(bfChinese_H, 13, Font.NORMAL);
            textfont_H = new Font(bfChinese_H, 10, Font.NORMAL); 
            textfont_B = new Font(bfChinese_H, 12, Font.NORMAL); 
            textfont_13 = new Font(bfChinese_H, 13, Font.NORMAL);  
            textfont_12 = new Font(bfChinese_H, 12, Font.NORMAL); 
            textfont_11 = new Font(bfChinese_H, 11, Font.NORMAL); 
            textfont_10 = new Font(bfChinese_H, 10, Font.NORMAL); 
            textfont_9 = new Font(bfChinese_H, 9, Font.NORMAL); 
            textfont_8 = new Font(bfChinese_H, 8, Font.NORMAL); 
            textfont_7 = new Font(bfChinese_H, 7, Font.NORMAL);
            textfont_6 = new Font(bfChinese_H, 6, Font.NORMAL); 


        } catch (Exception e) {             
            e.printStackTrace();    
        }     
    }


    /**
     * 设置页面属性
     * @param file
     */
    public Test(File file) {

        //自定义纸张
        Rectangle rectPageSize = new Rectangle(PageSize.A4);

        document = new Document(rectPageSize);
        try {
            PdfWriter.getInstance(document,new FileOutputStream(file));    
            document.open();
        } catch (Exception e) {
            e.printStackTrace();    
        }
    }


    /**
     * 建表格(以列的数量建)
     * @param colNumber
     * @return
     */
    public PdfPTable createTable(int colNumber){    
        PdfPTable table = new PdfPTable(colNumber);    
        try{    
//           table.setTotalWidth(maxWidth);
//            table.setLockedWidth(true);
            // 居中
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            // 边框宽度
            table.getDefaultCell().setBorder(1);
            // 向下10
            table.setSpacingBefore(5);
            // 设置宽度百分比
            //table.setWidthPercentage(100);
        }catch(Exception e){    
            e.printStackTrace();    
        }    
        return table;
    }


    /**
     * 建表格(以列的宽度比建)
     * @param widths
     * @return
     */
    public PdfPTable createTable(float[] widths){    
        PdfPTable table = new PdfPTable(widths);    
        try{    
            //table.setTotalWidth(maxWidth);    
            //table.setLockedWidth(true);    
            table.setHorizontalAlignment(Element.ALIGN_CENTER);         
            table.getDefaultCell().setBorder(1); 
            table.setSpacingBefore(10);
            table.setWidthPercentage(100);
        }catch(Exception e){    
            e.printStackTrace();    
        }    
        return table;    
    }




    /**
     * 表格中单元格
     * @param value
     * @param font
     * @param align
     * @return
     */
    public PdfPCell createCell(String value,Font font,int align){    
        PdfPCell cell = new PdfPCell();    
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);            
        cell.setHorizontalAlignment(align);        
        cell.setPhrase(new Phrase(value,font));   
        
        return cell;    
    }


    /**
     * 表格中单元格
     * @param value
     * @param font
     * @param align_v
     * @param colspan
     * @param rowspan
     * @return
     */
    public PdfPCell createCell(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setBottom(30);
        cell.setVerticalAlignment(align_v);    
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan); 
        cell.setRowspan(rowspan); 
        cell.setPhrase(new Phrase(value,font));  
        return cell;
    }
    /**
     * 创建无边框表格
     * @param value
     * @param font
     * @param align_v
     * @param colspan
     * @param rowspan
     * @return
     */
    public PdfPCell createCellNotBorder(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();    
        cell.setVerticalAlignment(align_v);    
        cell.setHorizontalAlignment(align_h);        
        cell.setColspan(colspan); 
        cell.setRowspan(rowspan); 
        cell.setPhrase(new Phrase(value,font));  
        cell.setBorderWidth(0f);
        return cell;
    }


    /**
     * 建短语
     * @param value
     * @param font
     * @return
     */
    public Phrase createPhrase(String value,Font font){ 
        Phrase phrase = new Phrase();
        phrase.add(value);
        phrase.setFont(font);
        return phrase;
    }  


    /**
     * 建段落
     * @param value
     * @param font
     * @param align
     * @return
     */
    public Paragraph createParagraph(String value,Font font,int align){ 
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase(value,font));
        paragraph.setAlignment(align);
        return paragraph;
    }

    static int pageNum = 1;// 页数
    public void generatePDF(Integer total,float width) throws Exception{
        pageNum = 1;
        // 每页数量
        int totalNum = 1;
        float SumWidth = total * width;
        if (maxWidth < SumWidth) {
            pageNum = (int) (SumWidth / maxWidth) + 1;
        }
        for (int i = total;i >0; i--){
            if (maxWidth > i * width){
                totalNum = i;
                break;
            }
        }
        while (pageNum > 0) {
            curPdf(total,totalNum,width);
            total = total - totalNum;
            pageNum--;
            if (pageNum != 0) {
                document.newPage();
            }
        }
        document.close();
    }

    private void curPdf(Integer total,int totalNum, float width) {
        PdfPTable table1 = null;
        if (totalNum < total) {
            table1 = createTable(totalNum);
            table1.setTotalWidth(totalNum * width);
            totalNum = totalNum - total;
        }else {
            table1 = createTable(total);
            table1.setTotalWidth(total * width);
        }

        table1.setLockedWidth(true);
        for (int i = 0; i < total; i++){
            PdfPTable table = createTable(2);
            table.addCell(getPar("全 宗 号","0901"));
            table.addCell(getPar("年   度","2020"));
            table.addCell(getPar("保管期限","30年"));
            table.addCell(getPar("机构(问题)","0901"));
            PdfPCell cell = new PdfPCell();
            Paragraph par = createParagraph("起", headFont, Element.ALIGN_CENTER);
            par.add(Chunk.NEWLINE);
            par.add(Chunk.NEWLINE);
            par.add(new Chunk("止",headFont));
            par.add(Chunk.NEWLINE);
            par.add(Chunk.NEWLINE);
            par.add(new Chunk("件",headFont));
            par.add(Chunk.NEWLINE);
            par.add(Chunk.NEWLINE);
            par.add(new Chunk("号",headFont));
            cell.addElement(par);
            cell.setMinimumHeight(160);
            cell.setRowspan(2);
            cell.setPaddingTop(20);
            cell.setVerticalAlignment(Element.ALIGN_TOP);
            table.addCell(cell);
            // 内容    字体  垂直对齐    横向对齐                                占列     占行
            PdfPCell cell1 = createCell("01", headFont, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1);
            cell1.setMinimumHeight(80);
            table.addCell(cell1);
            table.addCell(createCell("09", headFont, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(getPar("盒  号","00"));
            table1.addCell(table);
        }
        try {
            document.add(table1);
            document.newPage();
            document.add(table1);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    private PdfPCell getPar(String key, String value) {
        PdfPCell cell = new PdfPCell();
//        cell.setBottom(30);
//        cell.setTop(30);
        cell.setPaddingTop(10);
//        cell.setPaddingBottom(30);
        Paragraph par = createParagraph(key, headFont, Element.ALIGN_CENTER);
        par.add(Chunk.NEWLINE);
        par.add(Chunk.NEWLINE);
        par.add(new Chunk(value,headFont));
        cell.addElement(par);
        cell.setMinimumHeight(100);
        cell.setColspan(2);
        return cell;
    }


    public static void main(String[] args) throws Exception {
        File file = new File("D:\\test.pdf");
        file.createNewFile();
       new Test(file).generatePDF(6,110);
    }
}