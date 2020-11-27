package com.huaxia.opas.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.Channel;
import java.util.List;

/**
 * 输入输出实用类
 */
public class IOUtil {
	
	
	/**
	 * 默认缓存区大小，请不要随便修改
	 */
	private final static int DEFAULT_BUFFER_SIZE = 10240;
	
	/**
	 * 获取文本文件字符集编码
	 * @param fileStream
	 * @return
	 * @throws IOException
	 */
	private static String getFileEncoding(InputStream fileStream) throws IOException{
		String ENCODING = "GBK";
		byte [] head = new byte[3];
		fileStream.read(head);
	
		if(head[0] == -1 && head[1] == -2){
			ENCODING = "UTF-16";
		}
		if(head[0] == -2 && head[1] == -1){
			ENCODING = "Unicode";
		}
		if(head[0] == -17 && head[1] == -69 && head[2] == -65){
			ENCODING = "UTF-8";
		}
		return ENCODING;
	}
	
	/**
	 * 获取文本文件字符集编码
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileEncoding(File file) throws IOException {
		String ENCODING = "GBK";
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			ENCODING = getFileEncoding(in);
		} finally {
			close(in);
		}
		return ENCODING;
	}
	
	/**
	 * 获取文本文件字符集编码
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileEncoding(String filePath) throws IOException{
		return getFileEncoding(new File(filePath));
	}
	
	/**
	 * 列表转数组
	 * @param bytesList 列表
	 * @return
	 */
	public static byte[] array2bytes (List<Byte> bytesList) {
		byte[] bytes = new byte[bytesList.size()];
		for (int i =0; i<bytesList.size(); i++) {
			bytes[i] = bytesList.get(i);
		}
		return bytes;
	}
	
	/**
	 * 关闭资源
	 * @param channel
	 */
	public static void close(Channel channel) {
		if (channel!=null) {
			try {
				channel.close();
			} catch (Exception e) {
				// 关闭的异常可以忽略
			}
		}
	}
	
	/**
	 * 关闭资源
	 * @param closeable
	 */
	public static void close(Closeable closeable) {
		if (closeable!=null) {
			try {
				closeable.close();
			} catch (Exception e) {
				// 关闭的异常可以忽略
			}
		}
	}
	
	/**
	 * 关闭资源
	 * @param closeable
	 */
	public static void close(Closeable... closeables) {
		if (closeables!=null) {
			// 循环关闭全部资源
			for (int i = 0; i < closeables.length; i++) {
				close(closeables[i]);
			}
		}
	}

	/**
	 * 关闭资源
	 * @param in
	 */
	public static void close (InputStream in) {
		if (in!=null) {
			try {
				in.close();
			} catch (Exception e) {
				// 关闭的异常可以忽略
			}
		}
	}
	
	/**
	 * 关闭资源
	 * @param out
	 */
	public static void close(OutputStream out) {
		if (out!=null) {
			try {
				out.close();
			} catch (Exception e) {
				// 关闭的异常可以忽略
			}
		}
	}
	
	/**
	 * 关闭资源
	 * @param reader
	 */
	public static void close(Reader reader) {
		if (reader!=null) {
			try {
				reader.close();
			} catch (Exception e) {
				// 关闭的异常可以忽略
			}
		}
	}
	
	/**
	 * 关闭资源
	 * @param writer
	 */
	public static void close(Writer writer) {
		if (writer!=null) {
			try {
				writer.close();
			} catch (Exception e) {
				// 关闭的异常可以忽略
			}
		}
	}
	
}
