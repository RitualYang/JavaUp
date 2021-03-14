package com.wty.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 图片格式转pdf
 */
public class Img2PdfUtil {

	/**
	 *
	 * @param outPdfFilepath 生成pdf文件路径
	 * @param imageFiles 需要转换的图片File类Array,按array的顺序合成图片
	 */
	public static void imagesToPdf(String outPdfFilepath, List<File> imageFiles) throws Exception {


		File file = new File(outPdfFilepath);
		// 第一步：创建一个document对象。
		Document document = new Document();
		document.setMargins(0, 0, 0, 0);
		// 第二步：
		// 创建一个PdfWriter实例，
		PdfWriter.getInstance(document, new FileOutputStream(file));
		// 第三步：打开文档。
		document.open();
		// 第四步：在文档中增加图片。
		int len = imageFiles.size();

		for (int i = 0; i < len; i++) {
			if (imageFiles.get(i).getName().toLowerCase().endsWith(".bmp")
					|| imageFiles.get(i).getName().toLowerCase().endsWith(".jpg")
					|| imageFiles.get(i).getName().toLowerCase().endsWith(".jpeg")
					|| imageFiles.get(i).getName().toLowerCase().endsWith(".gif")
					|| imageFiles.get(i).getName().toLowerCase().endsWith(".png")) {
				String temp = imageFiles.get(i).getAbsolutePath();
				System.out.println("图片路径："+temp);
				Image img = Image.getInstance(temp);
				img.setAlignment(Image.ALIGN_CENTER);
				img.scaleAbsolute(597, 844);// 直接设定显示尺寸
				// 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
				//document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
				document.setPageSize(new Rectangle(597, 844));
				document.newPage();
				document.add(img);
			}
		}
		// 第五步：关闭文档。
		document.close();
		System.out.println("图片合成PDF完成");
	}

	public static void main(String[] args) throws Exception {
		String path = "E:\\files";
		String outpath = "E:\\files\\out";
		List<File> dir1s = getFiles(path,"dir");
		long tt =  new Date().getTime();
		System.out.println(tt);
		for(File dir1 : dir1s ){
			List<File> dir2s = getFiles(dir1.getPath(),"dir");
			for(File dir2 :dir2s){
				String fileName = dir2.getName();
				String outPdfpath = outpath+"/"+fileName+".pdf";
				List<File> imgsfiles = getFiles(dir2.getPath(),"file");
				imagesToPdf(outPdfpath, imgsfiles);
				System.out.println(fileName);
			}
		}
		long tt2 =  new Date().getTime();
		System.out.println(tt-tt2);
	}


	/**
	 * @Author：
	 * @Description：获取某个目录下所有直接下级文件，不包括目录下的子目录的下的文件，所以不用递归获取
	 * @Date：
	 */
	public static List<File> getFiles(String path,String type) {
		List<File> files = new ArrayList<File>();
		File file = new File(path);
		File[] tempList = file.listFiles();

		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				if("file".equals(type)){
					files.add(tempList[i]);
				}

				//文件名，不包含路径
				//String fileName = tempList[i].getName();
			}
			if (tempList[i].isDirectory()) {
				//这里就不递归了，
				if("dir".equals(type)){
					System.out.println(tempList[i].toString());
					files.add(tempList[i]);
				}

			}
		}
		return files;
	}
}
