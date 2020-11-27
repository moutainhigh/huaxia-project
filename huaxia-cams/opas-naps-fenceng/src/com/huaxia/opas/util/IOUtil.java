package com.huaxia.opas.util;

import java.io.InputStream;

/**
 * 输入输出实用类
 */
public class IOUtil {
	
	
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
				e.printStackTrace();
			}
		}
	}
	
}
