package com.huaxia.opas.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * 图像工具类
 * @author uatym990190
 *
 */
public class ImageUtil {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	
	/**
	 * 2维码转图像
	 * @param matrix
	 * @return
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}
	
	/**
	 * 2维码输出图像文件
	 * @param matrix
	 * @param format
	 * @param file
	 * @throws IOException
	 */
	public static void writeToFile(BitMatrix matrix, String format, File file)
			throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + file);
		}
	}
	
	/**
	 * 2维码输出到输出流
	 * @param matrix
	 * @param format
	 * @param stream
	 * @throws IOException
	 */
	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format "
					+ format);
		}
	}
	
	
	/**
	 * 获取2维码base64字符串
	 * @param text 2维码内容
	 * @param imgFormat 图像格式
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param charset 内容字符集
	 * @return
	 * @throws IOException 
	 * @throws WriterException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String get2DImageBase64String(String text, String imgFormat, int width, int height, String charset) throws IOException, WriterException {
		String returnValue = null;
		
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, charset);
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(
			String.valueOf(text),
			BarcodeFormat.QR_CODE, 
			width, 
			height, 
			hints
		);
		
		// 默认png
		if (imgFormat==null) {
			imgFormat = "png";
		}
		
		// 转化字符串
		StringBuilder sb = new StringBuilder(4096);
		sb.append("data:image/");
		sb.append(imgFormat);
		sb.append(";base64,");
		sb.append(convertImageToByte(toBufferedImage(bitMatrix), imgFormat));
		
		returnValue = sb.toString();
		
		return returnValue;
	}
	
	/**
	 * 获取2维码base64字符串
	 * 默认选项：
	 * 图片格式 png
	 * 图片宽度 300px
	 * 图片高度 300px
	 * 内容字符集 utf-8
	 * @param text 2维码内容
	 * @return
	 * @throws Exception 
	 */
	public static String get2DImageBase64String(String text) throws Exception {
		return get2DImageBase64String(text, "png", 300, 300, "utf-8");
	}
	
	
    /** 
     * 将图片转换为BASE64加密字符串. 
     * @param imagePath 图片路径. 
     * @param format 图片格式. 
     * @return 
     * @throws IOException 
     */  
    public static String convertImageToByte(BufferedImage image, String format) throws IOException {  
        ByteArrayOutputStream baos = null;  
        String result = null;  
        try {  
            baos = new ByteArrayOutputStream();  
            ImageIO.write(image, format == null ? "png" : format, baos);  
            byte[] bytes = baos.toByteArray();  
            
            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
            result = encoder.encodeBuffer(bytes).trim();
            
//          System.out.println("将图片转换为BASE64加密字符串成功！");  
        } finally {
        	if(baos != null) {
        		try {  
                    baos.close();  
	            } catch (Exception e) {
	            	// 异常忽略
	            } 
        	} 
        }  
        return result;  
    }
  
    /** 
     * 将图片转换为BASE64加密字符串. 
     * @param imagePath 图片路径. 
     * @param format 图片格式. 
     * @return 
     * @throws IOException 
     */  
    public static String convertImageToByte(String imagePath, String format) throws IOException {  
        ByteArrayOutputStream baos = null;  
        String result = null;  
        try {  
        	BufferedImage bi = ImageIO.read(new File(imagePath));  
            baos = new ByteArrayOutputStream();  
            
            ImageIO.write(bi, format == null ? "png" : format, baos);  
            
            byte[] bytes = baos.toByteArray();  
            
            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
            result = encoder.encodeBuffer(bytes).trim();  
            
        } finally {  
        	if(baos != null) {
        		try {  
                    baos.close();  
	            } catch (Exception e) {
	            	// 异常忽略
	            } 
        	} 
        }  
        return result;  
    }  
      
    /** 
     * 将图片流转换为BASE64加密字符串. 
     * @param imageInputStream 
     * @param format 图片格式. 
     * @return 
     * @throws IOException 
     */  
    public static String convertImageStreamToByte(InputStream imageInputStream, String format) throws IOException {  
        ByteArrayOutputStream baos = null;  
        String result = null;  
        try {  
        	BufferedImage bi = ImageIO.read(imageInputStream);  
            baos = new ByteArrayOutputStream();  
            ImageIO.write(bi, format == null ? "png" : format, baos);  
            
            byte[] bytes = baos.toByteArray();  
            
            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
            result = encoder.encodeBuffer(bytes).trim();  
        } finally {  
        	if(baos != null) {
        		try {  
                    baos.close();  
	            } catch (Exception e) {
	            	// 异常忽略
	            } 
        	} 
        }  
        return result;  
    }  
      
    /** 
     * 将BASE64加密字符串转换为图片. 
     * @param base64String 
     * @param imagePath 图片生成路径. 
     * @param format 图片格式. 
     * @throws IOException 
     */  
    public static void convertByteToImage(String base64String, String imagePath, String format) throws IOException {  
        ByteArrayInputStream bais = null;  
        try {  
        	sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
        	byte[] bytes = decoder.decodeBuffer(base64String);  
        	
            bais = new ByteArrayInputStream(bytes);
            BufferedImage bi = ImageIO.read(bais);
            
            ImageIO.write(bi, format == null ? "png" : format, new File(imagePath));  
        } finally {  
        	if(bais != null) {
        		try {  
        			bais.close();  
	            } catch (Exception e) {
	            	// 异常忽略
	            } 
        	}  
        }  
    }  

}
