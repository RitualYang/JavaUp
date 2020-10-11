//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.pdf.*;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.lang.reflect.Field;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class PdfComposeUtils2 {
//
//	protected Log log = LogFactory.getLog(PdfComposeUtils2.class);
//	public static final int SIGN_WIDTH = 100;//Integer.parseInt(AppConfig.getProperty("commpage.sign.width", "100"));
//	public static final int SIGN_HEIGHT = 150;//Integer.parseInt(AppConfig.getProperty("commpage.sign.height", "150"));
//
//	public byte[] BaseToInputStream2(String base64string) {
//		BASE64Decoder decoder = new BASE64Decoder();
//		byte[] bytes1;
//		try {
//			bytes1 = decoder.decodeBuffer(base64string);
//			return bytes1;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * pdf合成
//	 *
//	 * @param url                   路径
//	 * @param bs                    合成图片
//	 * @param x                     图片X轴 多个合成-隔开
//	 * @param y                     图片y轴 多个合成-隔开
//	 * @param width                 图片宽度
//	 * @param higth                 图片高度
//	 * @param childDirectorynew合成路径
//	 * @param site                  合成页数，多页用-隔开
//	 * @param tx                    日期X轴 -隔开
//	 * @param ty                    日期y轴 -隔开
//	 * @param tps                   字体大小 -隔开
//	 * @param siteps
//	 * @return
//	 */
//	public String pdfConvertImage(String url, byte[] bs, String x, String y, int width, int higth,
//			String childDirectorynew, String site, String tx, String ty, String tps, String siteps) {
//		BufferedOutputStream bos = null;
//		String newurl = "";
//		try {
//			bos = new BufferedOutputStream(new FileOutputStream(new File(childDirectorynew)));// 要输出的pdf文件
//			// 将pdf文件先加水印然后输出
//			boolean b = setWatermark(bos, bs, 16, x, y, width, higth, url, site, tx, ty, tps, siteps);
//			if (b) {
//				String bast64 = PDFToBase64(new File(childDirectorynew));
//				return bast64;
//			} else {
//				return "";
//			}
//
//		} catch (FileNotFoundException e) {
//			log.info(e.getMessage());
//		} catch (IOException e) {
//			log.info(e.getMessage());
//		} finally {
//			try {
//				if (bos != null) {
//					bos.close();
//				}
//			} catch (IOException e) {
//				log.info(e.getMessage());
//			}
//		}
//		return null;
//	}
//
//	/**
//	 *
//	 * @param bos输出文件的位置
//	 * @param bs            原PDF位置
//	 * @param waterMarkName 页脚添加水印
//	 * @param permission    权限码
//	 * @param higth
//	 * @param width
//	 * @param y
//	 * @param x
//	 * @param newurl
//	 * @param site
//	 * @param tx
//	 * @param ty
//	 * @param tps
//	 * @param siteps
//	 * @throws DocumentException
//	 * @throws IOException
//	 */
//	public boolean setWatermark(BufferedOutputStream bos, byte[] bs, int permission, String xs, String ys, int width,
//			int higth, String newurl, String sites, String txs, String tys, String tps, String siteps) {
//
//		PdfReader reader = null;
//		PdfStamper stamper = null;
//		try {
//			reader = new PdfReader(newurl);
//			Field f = PdfReader.class.getDeclaredField("ownerPasswordUsed");
//			f.setAccessible(true);
//			f.set(reader, Boolean.TRUE);
//			stamper = new PdfStamper(reader, bos);
//			int total = reader.getNumberOfPages() + 1;
//			PdfContentByte content;
//			BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
//			PdfGState gs = new PdfGState();
//			String[] site = sites.split("-");// 签名
//			String[] sitep = siteps.split("-");// 字体
//			String[] tp = tps.split("-");// 字体大小
//			String[] x = xs.split("-");
//			String[] y = ys.split("-");
//			String[] tx = txs.split("-");
//			String[] ty = tys.split("-");
//			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
//			for (int i = 1; i < total; i++) {
//				content = stamper.getOverContent(i);// 在内容上方加水印
//				// content = stamper.getUnderContent(i);//在内容下方加水印
//				gs.setFillOpacity(1f);
//				content.setGState(gs);
//				content.beginText();
//				content.setColorFill(BaseColor.BLACK);
//				content.setTextMatrix(70, 200);
//				if (sitep.length == 1) {
//					if (i == Integer.parseInt(sitep[0])) {
//						content.setFontAndSize(base, Integer.parseInt(tp[0]));
//						content.showTextAligned(Element.ALIGN_LEFT, date.substring(0, 4), Integer.parseInt(tx[0]),
//								Integer.parseInt(ty[0]), 0);
//						content.showTextAligned(Element.ALIGN_LEFT, date.substring(4, 6), Integer.parseInt(tx[0]) + 39,
//								Integer.parseInt(ty[0]), 0);
//						content.showTextAligned(Element.ALIGN_LEFT, date.substring(6, date.length()),
//								Integer.parseInt(tx[0]) + 67, Integer.parseInt(ty[0]), 0);
//					}
//				} else {
//					for (int j = 0; j < sitep.length; j++) {
//						if (Integer.parseInt(sitep[j]) == i) {
//							content.setFontAndSize(base, Integer.parseInt(tp[j]));
//							content.showTextAligned(Element.ALIGN_LEFT, date.substring(0, 4), Integer.parseInt(tx[j]),
//									Integer.parseInt(ty[j]), 0);
//							content.showTextAligned(Element.ALIGN_LEFT, date.substring(4, 6),
//									Integer.parseInt(tx[j]) + 39, Integer.parseInt(ty[j]), 0);
//							content.showTextAligned(Element.ALIGN_LEFT, date.substring(6, date.length()),
//									Integer.parseInt(tx[j]) + 67, Integer.parseInt(ty[j]), 0);
//							break;
//						}
//					}
//				}
//
//				if (site.length == 1) {// 一次签名
//					if (i == Integer.parseInt(site[0])) {
//						com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(bs);
//						image.setAbsolutePosition(Integer.parseInt(x[0]), Integer.parseInt(y[0]));// XY轴
//						image.scaleToFit(width, higth);// 图片高度
//						content.addImage(image);
//					}
//				} else {// 多次签名
//					for (int k = 0; k < site.length; k++) {
//						if (Integer.parseInt(site[k]) == i) {
//							com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(bs);
//							image.setAbsolutePosition(Integer.parseInt(x[k]), Integer.parseInt(y[k]));// XY轴
//							image.scaleToFit(width, higth);// 图片高度
//							content.addImage(image);
//							break;
//						}
//					}
//
//				}
//
//				content.setColorFill(BaseColor.BLACK);
//				content.setFontAndSize(base, 8);
//				content.endText();
//				content.stroke();
//			}
//			return true;
//		} catch (IOException e) {
//			log.info("erroe1" + e.getMessage());
//		} catch (DocumentException e1) {
//			log.info("erroe2" + e1.getMessage());
//		} catch (NoSuchFieldException e) {
//			log.info("erroe3" + e.getMessage());
//		} catch (SecurityException e) {
//			log.info("erroe4" + e.getMessage());
//		} catch (IllegalArgumentException e) {
//			log.info("erroe5" + e.getMessage());
//		} catch (IllegalAccessException e) {
//			log.info("erroe6" + e.getMessage());
//		} finally {
//			try {
//				if (stamper != null) {
//					stamper.close();
//				}
//				if (reader != null) {
//					reader.close();
//				}
//			} catch (DocumentException | IOException e) {
//				log.info("erroe7" + e.getMessage());
//			}
//		}
//		return false;
//	}
//
//	public String PDFToBase64(File file) {
//		BASE64Encoder encoder = new BASE64Encoder();
//		FileInputStream fin = null;
//		BufferedInputStream bin = null;
//		ByteArrayOutputStream baos = null;
//		BufferedOutputStream bout = null;
//		try {
//			fin = new FileInputStream(file);
//			bin = new BufferedInputStream(fin);
//			baos = new ByteArrayOutputStream();
//			bout = new BufferedOutputStream(baos);
//			byte[] buffer = new byte[1024];
//			int len = bin.read(buffer);
//			while (len != -1) {
//				bout.write(buffer, 0, len);
//				len = bin.read(buffer);
//			}
//			// 刷新此输出流并强制写出所有缓冲的输出字节
//			bout.flush();
//			byte[] bytes = baos.toByteArray();
//			return encoder.encodeBuffer(bytes).trim();
//
//		} catch (FileNotFoundException e) {
//			log.info(e.getMessage());
//		} catch (IOException e) {
//			log.info(e.getMessage());
//		} finally {
//			try {
//				fin.close();
//				bin.close();
//				baos.close();
//				bout.close();
//			} catch (IOException e) {
//				log.info(e.getMessage());
//			}
//		}
//		return null;
//	}
//
//	public BufferedImage uploadFileBase64(BufferedImage imagedown, BufferedImage imagelike, int x, int y, int widths,
//			int higths) {
//		int width = imagedown.getWidth();
//		int height = imagedown.getHeight();
//		int widthown = imagelike.getWidth();
//		int heightown = imagelike.getHeight();
//		BufferedImage imagetwo = new BufferedImage(widths, higths, BufferedImage.TYPE_INT_RGB);
//		Graphics2D gtwo = imagetwo.createGraphics();
//		imagetwo = gtwo.getDeviceConfiguration().createCompatibleImage(widths, higths, Transparency.TRANSLUCENT);
//		gtwo.dispose();
//		gtwo = imagetwo.createGraphics();
//		Image from = imagelike.getScaledInstance(widths, higths, imagelike.SCALE_AREA_AVERAGING);
//		gtwo.drawImage(from, 0, 0, null); // 绘制缩小后的图
//		gtwo.dispose();
//		Graphics2D graph = imagedown.createGraphics();
//		graph.drawImage(imagetwo, x, y, widthown, heightown, null);
//		graph.setStroke(new BasicStroke(1f));
//		graph.dispose();
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();// io
//		try {
//			ImageIO.write(imagedown, "png", baos);
//			BASE64Encoder encoder = new BASE64Encoder();
//			String invoiceBase64 = encoder.encode(baos.toByteArray());// 返回Base64编码过的字节数组字符串
//			return imagedown;
//		} catch (IOException e) {
//			log.info(e.getMessage());
//		} finally {
//			if (baos != null) {
//				try {
//					baos.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}
//
//	public static void main(String[] args) throws IOException {
//		PdfComposeUtils2 composeUtils = new PdfComposeUtils2();
//			String base = "iVBORw0KGgoAAAANSUhEUgAAArAAAAGQCAAAAABzawzBAAAABGdBTUEAALGPC
//			/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QA
//			/4ePzL8AAAAHdElNRQfkARQPICV2xq2UAABpBUlEQVR42u2dd5xmVX3/P6fc
//			+tTpZTu9Kc2K2EGDxoIlJtEYY40tP2NJYkuiJmpMjCYxxt6wxYYIFlTUWFBREEQQdoHtZfrTbj3l+/tjdhbYZZiZ3Z3ZeeC
//			+/4DXztznPvee+57vPfV7GKGgoHvgx
//			/oCCgqWQiFsQVdRCFvQVRTCFnQVhbAFXUUhbEFXUQhb0FWsGmGj7Rf43gW7MlgDzP6noOAQ2GoZONh8JjcQdOPxRIyBaNX8JRWsKlaNF
//			+9K/Szzk3eAcRAYO9bXU7A6WTURtjS4rcQ663enEpZ44WvBPbN6hE0pZIlBOxDWCkaFsgX3xKqpEvTw/jjpRxgpAAz2WF9Pwepk1Qhb5hN
//			+MI4gygGgELbgnpHH
//			+gLmsL3BVrZpUuUGjBXCFszDqhF27S8nuLODqelNhCxg93JdRMZAOM2yaCI88IbgABEIfH8PgzUqS7PcjOVxq9mK8qk8ajZacd4ECHepuM
//			/Vle2d57kbVjInu/iNJ5WPdQEVAFhFwvoEIgAxu1Oie8SCc8eYmUCP9TlJPLD/x1rF7VYnVnuTxuTYZDMdt0ZpbSz22+kQkQUg5jntfN
//			/Jq3yy/yuXX33OsS6gAgCrSNgeCxARn2Zg4l6cJRAx2LzV87v/uGorjt+x/+ceWWMsAQATQvJ4f8RlYOCc8RSMSc5ZPs95
//			+YHz353K9KCY3Bi/6TvHuoAKAKwiYQcNyILYPk6M070MHHDSBGM3XPLPt8pN+26f
//			+zERAM7gWW20zgCAMcaYIYIxQEjWGkPzRtj5aAfjPXan/OGxLp+CWVaNsMOWg0ByD7fgNH+E1Q6zWjhy++VbKu3tBG
// zxlZawkwBHAheAwGEIFjto6RALi3TpG5OmzR/7u6WTXCDloO4pbvYwRm5o+DxgG4x+ima20d7Wpr7hVPAONC8JystVZBgOZaV7T/Nc8YY2yp3Q
// +V5uCU2RiffazLp2CWVSNsLxgI4FOwdG9WcUvksLzxoe2YiuGV54TlZI0xAGPMEVywhBiB2IGYyonI2vkbXfPVYdu91vRv8955rMunYJZVI2ww
// +z8Wg4jR/COzDpRx0NhxhVuLEGbJ3DxEBjAhOEsIRmG2mwsgWCIAjOm5o+ZhTtiD5zXaFgtnnvXGk451
// +RTMsmqElb4FwCjtOBySKWee44gzNtNPLyy3reLSOyDs7PufczEbqckKx/d8Rwy7pWqtGrp1t1StVUrOyIHjiRlF0tXEBEPm7fzax34XOk04BJ8SC
// +FWjnvURQ8KAVZUbFcPq0bYqiapLTORK7QU8zfmtXDb/fn/bmufaLa6ndbwxP6fC+mG5UrZGwp6Bob66/4gF450BE8Y55wzljN
// +tzlgCZdcSqAZuDYVrveeb/2YKiz1KpOy0jKuOv+sCx86iDQ71uVScHdWzWyt7RtR7kBgdDvrlGHnbc6nfiqc3RfcUulQb
// +42rztl7hcEAmNcgqw1xvpgjM3FRiISAFlL5B04E1mjlOllKi9FX/rQ5rbxKGeVViW1Xn7WX7wEVlnhC1tE2FXFqomwfYDLiKtphpyYmVdYL+dSf
// /4WmQ602kL89elzja4As0amnAvhYHaEwRIAxhljaDHOOb/ztJYzLlwYtivo+8W7v8ciRxpyqQ1Vm8Q/vC6wjHk2a+aDx7pgCu7GqomwFOQ9M
// +TmLHWne1juznvg2JDacf44ZyduTQYmsunh/T9OueQAYAGyxtjK/m4tQSBLRJLdfTBCS8qsJzDdu/ldlwp3XymCx3O
// /J552Xvn2ysSANiSc2fMVEXYVsWoiLKu1FBhx26567F5WdJmeqb4P7gOJm9lQ6/XO8Fwl0wdglLYe49xxZkVjYAwMjBPIzMZc
// +PuP5yBlJVP2bZeMoUUyKoWqbaLUvvjv1+3V/TvXcjDSWV4/1gVTcDdWTYTFKeNRLojbzYOhMHzeoJa7u8Yer01WMpVxvzETVvf
// /POWcMzAQgay15DIwMLDZRhdgD1onljocDNs2/+2uSXgu5cZVmg1U7KdOcpNqQA4ZQ1zyIsKuMlZNhDUlrwGuBOvUKzDCzueIu2/t3zRx
// +k02ScofT4bHK/t/7oOsJTDGwAVmBwAYgAAgMhZidi4MyTuPp
// +S6j31eyyriNnOcJKi16Plv0VrWLOe55ZLD5tZd6MILVpRVIyw2XSeMkcS2npk6zvwxbXLgpi
// +UO1NCCzb8hMp4350Hsrt1hd3lBIxBzI5gMSBzjchdK3Tij33p33ePTijyBHFjeVL9f2
// /snex3AQHMeiqWOlVmFaFJMkOSGLQVAntHELlO1u5jIGIMdv8Lh4ykKFTeEX7birF6hPX3O7ZAz2fv3r/1/M4+Zhh/R5X3Lfll7Uaqx6NUisqP
// /vGn1bW73L5O5JIqJf7Fbzh979SJx7oYjhakDTmcrBJCCGg9TMoXke4f8wOHQ2sfIGssxaXpIODueLf0hqwaYamyv67YxL1WqyfW/NRM9oux
// /snzntOosyWvSWvWkUelEm7/2Gf2BHGb8amw3Ompbr/gNRc1J9fhthOOdUEcJRi3YCDjkOUMwk71uRkvWQwB1nIpU8Y5F4Dx85B3yt3i6
// +oR1vbOzgTENO51ifdQljI+CTD8tc11XFvq94QqZz2446Z3b54JvRnUGywerO1ec+lFoJ54c
// /99xVdAMGhG1gFMqoUXsrzhljksIwMGeAwAiMrKGoGZnmN9uYtl1QhLfeBgBJq49wirZ572pUFKksYjn5wPQc4752A+mMwiMX3Je
// +JSTyNGEIepMz76vuemHqwOT+Dd8+QWIBdcWyHFjPC8UMNxLIbQyvo7vsuRtuI0Gt+5bce+zp6Jtac979n3MrS4ylg1wtpeMAZGmL53Yfes
// /7fr9qbMPOpvhERSWnLSOKddkpe984bShl0R6nECbZ0/e+Px7d4OEw44Sse6HI4WjINzB+QH0bc
// ++uPK6e89KYhYFRS2dtz8299tGYsZA0Dg6c47orUPnxo44m9cIWiVEH0FZeY6UlxMLWvJzntgSp997lDvU79FDZU07aLPb
// /fzE5p6iUSdoTzkAL50nvpDMooaRGZiT3qsS+HoYYkMUWsrffpcoNrff8KXiJo7//fF55w6EjIAXEjXdV2H9/jAn
// +hjfbmLZtVEWPL3d0Ut0EtAefbci8PpOtnQuH4ULPVVdv47/2d3b9RktUbHLUV55T2POkVpn0IkvJ9Y5HdxR9bdYKQZz
// /fc9vhWHOoWwsbnf7n5xsmMGBFxAWtBhizARHrGLT+c7lk1IizAqrlOZ4i7kVDG2YG4glzO2/AqgwLqAYMEKFzwvKkPa+BocvK0JDqNv
// /08G5rUlcgwJiM8+w1nM3IcgkAAsG6qERARgTMLgBhnljGyxLnlIENcUKMkt/3PF3bv739O5BVkiQkGSwyacb4
// /iQMFyI1T0qtGhAVYNdcpvNklLSYHcBTHi/00LzspHLRFFZNf/s9bqvG+vv6dti2c7LS
// /fm7QrSlm7IEePQYGEAcYBxkNgMgS9Wx721fSWhDvP0ox6ZDWCEkTAMNmF2LAZt4WeszCf/irhdUjbIlZEGMmscwexcH7Rt1Hx4RWVVT6+3/7glyzm
// /VPTbFSZUI9982bWl0zwnMwDGS1UrYPAGljXAspQJobK10Gfe3bv8WHxPSBLkJOSjHpisRyKawxLllLBHKnXO/hf4lG
// /Vjf0CJZNcKizDQzAkghiR29BLF1FbllZFPDSC/55z19MwlKTarUdkUPeM3FDu/bNXqs7
// /swMYwL4QEdxqWUjDlGWQGrSzyd3Pnba267JquIVn7nq6rH5JlRWvjWGEVADgbOwJgZfdDF56F+rO9nsaye2VpRXSryUqo2opDdW
// +aXg25ggdPqyPag5QSd7e/9JEqxl1Yjc/LMeP1xbz4nljINLQMDoftS1M
// /mYWCzrwiVgzvSTO0cu2zXrbsygFEZHVbO9Fx5EZgALCyXICb4xvLg2nWj/eFIyR9A1O7tlkk+q0ZYzRwBHSbcSXJ5L4lflirsvmG0UcHeb
// /znzZU881i9FftGnfLKV+0tV9qs3Kh2qbCazxZSbCyTULp5609/uWUytZ6xwuFGydyGlPCTbt1/fGhyC8/3WsIfOP2xjz8l5pyDrG0NdTwk5a65
// /VUjbBIwOCqMJZQhJuYdeVmqsMCeaE1w5Ycu6807vlHVlue1nJe/7LTdo3mr4mmnWyNsJF1GeabqE7dv3rxl6
// +QO6flSp5kGhJCCdbjrZyoXc41KAuDX6uFfrTt5DaNGp49LMZvmsaWrLlrVw7+UFWXVCNuuzArraEuacTtf5XrJVYIJObD7fz6
// /tRoxXyuYQCYnvO0ix4GVDECr3KXCwrT2bLl5y8TVWUqCGetaM9vu59wagpAZ3Ny1XO0/nK87+6HnnDgUaAlr969tI2PIn
// +rT0qZd002waoSF9slKwyxXJOxd6gSpDpUHxCEZ41Ectsst3zcHSnheYS2HTkSom303/u233dpMKU89Rynqe8y7jo+m1h
// /r212QluuwhusTE4yU5blwBRkmCDab2HzDtq3btibCJTM3Nm0doUgybV1ptLUAd7jWbtCQZMIHXviQxwkhGCx1
// +8DIqhe2WZIwhnRgSOaGBxNeFcqIAwF4PmGTXJbQMb7X+My/7huJpkuJ7etkfVNnvfMi2rUOk/3H
// +n4XwjDlgRqsxDo6DJFKkezefNvEnr137E2EK1uWCWjwA89PQpGEdslakkGQpjnAGU5
// +0JMesYYpxbgUANlC2KPFPMK2y6xVZgwxYzYEtaqsExrG6c5u83umU0bT1rje84avWQswctZvtbz3zX/Jo5BxtuqfW6eMfXG1H1EJOZfJ3l
// 8Fs7ZI1iR2nLYAkQzBCcA6mamCEIskECJjSANSdt2HDqWcc5IG25g4wLjvvAhn2rXlggyntgBCyu+eAl9Se88eQ8EIIODCXO3
// /+VIqDNN334uxieoJ5GNcpHmyf/2zm1pF0Kc8N9rG5MJlxmsjiQDtc3fumKLeDQs51UnMPawSiGw
// /SBBA4WAOfWAtJXmdf74nMePgQF0RaOw6G1mE180/XbSa1+Ya1xkGb+BPvRu26q9+x55CufyslAzl33fA8gCjjpWz
// /2qebp0TYmlZtXWPp3f597O4c9BTjZqh/iypibxm6ZaOqnX/huq3dKeJRZJySda3DHyTR8H0l6IOsiMQFrwTwR0/F
// /9uxTTZrLkjACsAZcmNm8DIWwR415hCUtWSPwcO17v0gDDcXtX7yffCYONB7mfQBjYeWKf7y2StRhYcR6svjcT55+24nEWlGPn7DVHmCR
// +oizOovthz+yvSQ6yUgjcXqdTgPCFaQUgQkYEnKuF0CAMaOZW53AuX
// /2tI3oOA4HdJz5vkNgoPvIWvVVLyw6oZ0ZyK9649ZW3ba8DTuPu8rWuRcHczcwz+muP2vPG79YKu1ycziqVNk3/DcvU6g1Sls3ujZnqjO84AUd4
// +KQWiL51tcvz5inO+CWuVbBL6dGGZKuTMlYMH4gla4gDkWl+u5nvOQRFRiT+jJLWNkBGWO5EGw25
// /Oxvq0jZtULSzO9yN0fvXCrT1ZxYtZLGM30HHi1zfsIvvbhnyScWzu6j4b34px3H38cEo9riabvstX
// /atTC7v7Cl26wHlnDBKxDRMYADlkLcGbJ6e1nE+N3btskYPjA8H+eMKJzR2SBtYLDKN9azkFG3pmqoas5dpNfiBiDNp7V4JyBcgM
// /LcXC8DjkqW8YEeew0/1T6Lvi7VuZtWt2mapO3tKuJvV7SL5FOXORM3KhZdoc+ttvbfZtBuHvYT172cs/ABB8kABqwCp8cJrAmSVIBkscVn77Ez
// +acjybEcJckZswRkwKEYdkq4MbT3jcBu/knY+fGBzzMmHBDXyt+x
// /3508iQEogYEIAEAKcA2ByNd7z4XDshGVggJSZcAGkWTUMOM8iEINDliwHmdwa0zNZc7745jtOvs3vbD9td2qe/6AyZjMI3Z3pHs8ok1OojJsEtz7
// +926en7tzPHCVnj7vNU+ZWO1rlmQeWT9EPl4TKmC3//odjaYuIVO9thWjohOfRNC3dk3vAwb7+voD4WXKu+lRHGP1NiCI
// +qfS4T94wfmi2S1DrIfLMasSEAMs+IEqVuJKcLCSErut4K6TccYYByJffO3tt5XHsGlr1Zmxr3r
// +cX1kBR2IsHNho1FDxEKGSHqJ96WXtcEcBSYzN68+/6197fpCvQrHmsillAKhvdzFTZ//2q2zDSXXa0swBTjqrKc96QFB1mGu5wC5Kil90c/z
// /klIV9ugjQc887knYJrV5863Wu/zSDlmwloOa4gLwzlgDVnfL7M4hui5fkhS2tx36//99NYcrNqsoF0a2CXTk28Nnbc
// +Zw1TRvI7R3jmHgypDKFA7qo8eOdbOe8dRxgHpUmc/Zo/sR72Dh90/GqjUeXKeAx2/JsfuUZ6UYlxk2kgSFADD4f+uzZQdchCam4z7rMp/Pk3y
// +50D5uGm7PHvuiZaQfVyp1zLVbrfR4px7DRRZYgWG6VJuk6IgkBeILbt6aTt9+xhSB9F0p5TdTZDDxdbbLRjz4yYCZj0nLnkAcTswBJYvujPX
// /39b5JZn1lT9ve6X3UO85omXoarPoHqbRw9Z49n/vFZoEZ1BuA4wuizgnJzuAVf3qcDQJYxXiH1dAWodn34X+2DuM5DU7ax7z2sZw5jDHkZq6
// /btXe55FyzNbrWq0tkdWGiCiPaPcpVQcca7CBAVW3Z67kT1gD2VutSycY
// /J6h8VzluYrTA8u2D5wvUyZu5XTrVQ9B4PUAlT4OPPTv020xzUxTfsjxq412RJ3vvOo8lErwRgd5TygYA
// +A7j77S0mRkyGaddk6kiJqGdvwVel3HkRjE8OeJGsrYZKbRyWjV3+cRcuwirLWCmU5UAtTU5t9u/bqzT/RkiXbzeiSVGwOOx40yJHMeptpnyen
// /9ohKo5a70HfNVHigW4eZxJb1xNfeNt0TZ+hpmYDHz37duQKNeBStvO+g41cbMZXUVR+8MndzVs8jb+OtrmszG5Yf88pHtnatq3SM63FobdyWJNR3f
// +Vv8lBkI9vX7LnoRRdjGzZt6ak5IMMX7O7rco5ZL4G1jCGdmPjF5mu3tImob7p3eopQSUWsrHG5sCYigJdbzNPCQ6gvfqLZO0KMWc3dQ
// O0lCIQPz6q/9WZTOho2cqTqP2jucOdnSpXm944aqf7xkQbfvud/MT91rddny9WSK2/sP++AmbslbpVKYDYZTlwnHhhwz55a9xhvehur1nd
// /CIx5vGRlInmCwNpOzWRcCLZiUiLDHAaO6AGKWOJSmgspt33XjdrftiIxlnVhPTtbaXuVk4tyw5SLz+XT2ZUuCCk7b4s1c8SOaMzfaHHbroK/Owa
// +3ln/w6wWUZpC7HD3n902Qczs1mOuLpWZoJ5GypqbwWT7PE66kbubOLr0mxtU/800eJ1lzC5kRKZolzwydFz0dfPjARxNTXLDU/de6pQktEC84Pvo
// +w7MLulVK6DiNuwYmBmOVo/fDT3xZGW+Z4kvJckfAc4h1XC63mClyQcRW5MpPCGuZ5/++hD61aw5llwKy2BzHW4
// /7yb35aiwb3GtEzCf9lrziJlMnnHviRTqsjlmeOb1v1ZSonsmL7iSo0yAApjXrYHz7nBNvk4dwrUHNOlrjIPKRfeYO3nfWqdljf
// /b6nDZZy7SGZSwFSCHtUIGuEQDtibqn1uysuu40P7WWCgwwRuMONtl4GwPdbc9fj5AzDExz1uIPRC5/zqE6Pa5SUMPuHHA5
// +MHlSy177qXgg3I5Ttphq7xsuPLE92VM/kPnoSB9kXgYRR75cq0vz8Z7S6y+9Q5je8ky7/4JHvRRaM1fepZuKrAEXsS9+fPE0uKuM47df
// /c8VKCVdmIXmB99XWPY6bIsxJh0uhLa8UgZ94kffnhb9am9A1loLBJnNIBxem6nEseIH9jbI8dITL
// +yveBO1MqySwuc6Y2xeX5FWMfYtp9psuGrHml0bXv7yrMXXC5sFS7ze
// +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";
//			byte[] bs = composeUtils.BaseToInputStream2(base);
//			String childDirectory = "F:\\home\\syniapp\\广东非居民低压.pdf";
//			String childDirectorynew = "F:\\home\\syniapp\\广东非居民低压1.pdf";
//			String[] sitexy = "330-80,140-130,1".split(",");// xy页数
//			String[] sitepx = "457-0,148-0,12-12,1".split(",");// xy，大小,页数
//			// 350,110,1 低压非居民背书合同
//			String x = sitexy[0];// x
//			String y = sitexy[1];// y
//			String site = sitexy[2];// 页数
//			String tx = sitepx[0];// 日期X 0不需要配置
//			String ty = sitepx[1];// 日期Y0不需要配置
//			String tps = sitepx[2];// 字体0不需要配置
//			String siteps = sitepx[3];// 日期
//			String ints = composeUtils.pdfConvertImage(childDirectory, bs, x, y, 60, 60, childDirectorynew, site, tx,
//					ty, tps, siteps);
//	}
//
//}
