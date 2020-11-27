package com.huaxia.opas.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Md5 {
	
	private static Logger logger=LoggerFactory.getLogger(Md5.class);

	public static String getMD5Str(String str) {
		
		MessageDigest messageDigest = null;
		
		try {
			
			messageDigest = MessageDigest.getInstance("MD5");
			
			messageDigest.reset();
			
			messageDigest.update(str.getBytes("UTF-8"));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		
		byte[] byteArray = messageDigest.digest();
		
		StringBuffer md5StrBuff = new StringBuffer();
		
		for (int i = 0; i < byteArray.length; i++) {
			
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1){
				
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			
			}else{
				
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
				
			}
			
		}
		
		return md5StrBuff.toString();
		
	}

	public static void main(String[] args) {
		System.out.println(Md5.getMD5Str("12345678901234567890aaab"));
	}

	
}
