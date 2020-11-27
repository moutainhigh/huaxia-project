package com.huaxia.opas.util;

import java.util.UUID;

/**
 * UUID生成器
 * @author Administrator
 *
 */
public class SequenceUtil {
	
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

}
