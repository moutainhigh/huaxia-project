package com.huaxia.opas.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.sun.crypto.provider.SunJCE;

/**
 * @Description: 采用AES方式加密
 * Copyright: Copyright (c) 2016  版权信息
 * Company: HUATENG  公司信息
 * @Author zhang.xinchun
 * @Version 1.0    版本信息
 * Created at 2016-5-9 上午11:25:52  创建日期
 * Modified by XXX at yyyy-mm-dd 修改信息  
 */
public class EncrypAES {
	private Cipher c;
	//保存加密结果
	private byte[] cipherByte;
	public EncrypAES() throws NoSuchAlgorithmException, NoSuchPaddingException{
		Security.addProvider(new SunJCE());
		//指定AES算法
		c = Cipher.getInstance("AES");
		
	}
	/**
	 * 
	* @Description: 加密
	* @author zhang.xinchun
	* @version  V1.0
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	public byte[] encrypt(String sSrc,String sKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		if(sKey != null){
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			c.init(Cipher.ENCRYPT_MODE, skeySpec);
			cipherByte = c.doFinal(sSrc.getBytes("UTF-8"));
			System.out.println(new String(cipherByte));
			String tempStr = parseByte2HexStr(cipherByte);
			Base64 base = new Base64();
			return base.encode(tempStr.getBytes("UTF-8"));
		}
		return null;
		
	}
	
	private static String parseByte2HexStr(byte[] buf){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if(hex.length() == 1){
				hex = '0' + hex;
			}
			builder.append(hex.toUpperCase());
		}
		return builder.toString();
	}
	
	private static byte[] parseHexStr2Byte(String hexStr){
		if(hexStr.length() < 1){
			return null;
		}
		byte[] result = new byte[hexStr.length() /2 ];
		for (int i = 0; i < hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 +1),16);
			int low = Integer.parseInt(hexStr.substring(i * 2+1, i * 2 +2),16);
			result[i] = (byte)(high * 16+low);
		}
		return result;
	}
	
	public String decryptor(String sSrc,String sKey) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException{
		if(sKey != null){
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			c.init(Cipher.DECRYPT_MODE, skeySpec);
			Base64 base = new Base64();
			byte[] encry = base.decode(sSrc);
			encry = parseHexStr2Byte(new String(encry,"UTF-8"));
			
			cipherByte = c.doFinal(encry);
			return new String(cipherByte,"UTF-8");
		}
		return null;
	}
	
	public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, UnsupportedEncodingException {
		EncrypAES des = new EncrypAES();
		String password ="camsonline123";
		byte[] buf1 = des.encrypt(password, "huateng2spdbcccc");
		//N0NCMzI5Mjk4NjVEMUE3MjFCOEM0MzI2Nzc1M0U4MUY=
		System.out.println("加密后："+new String(buf1));
		String buf2 = des.decryptor(new String(buf1), "huateng2spdbcccc");
		System.out.println("原密码："+buf2);
		System.out.println(new String(buf1).length());
	}
	

}
