package com.huaxia.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;



/**
 * 
 * Description: 鉴于一些公共小方法不便单独分类放置，故提供该公共Util类统一存放 Copyright: Copyright (c) 2018
 * Company: HUATENG Author liyanan Version 1.0 Created at 2018-12-29
 */
public class CommonUtil {

	public static Socket getSocket(String yzIp, int yzPort, String sjsIp, int sjsPort, int timeout) throws IOException {
		// 先连亦庄
		Socket socket = new Socket();
		SocketAddress socketAddres = new InetSocketAddress(yzIp, yzPort);
		try {
			socket.connect(socketAddres, timeout);
			return socket;
		} catch (IOException e) {
			//不做操作继续往下执行
			socket.close();
		} finally {
			socket = null;
			socketAddres = null;
		}
		socket = new Socket();
		// 连接备用socket地址
		socketAddres = new InetSocketAddress(sjsIp, sjsPort);
		socket.connect(socketAddres, timeout);
		return socket;
	}
	
	
	
	
	/**
	 * 生成给定位数的空格串
	 * @author qingfeng.xiu
	 * @param len
	 * @return
	 */
	public static String checkLen(int len) {
		StringBuffer sb = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			sb.append(" ");
		}
		//String str = sb.toString();
		return sb.toString();
	}
	/**
	 * 
	 * 右补空格 作者：wangkai
	 * 
	 * @version 2016-5-24
	 */
	public static String formatStr(String str, int length) {
		String charsetName = "GBK";
		byte[] returnByte = new byte[length];
		if (str == null) {
			str = "";
		}
		int strLen = 0;
		try {
			strLen = str.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
			System.err.println(String.format("不支持的编码[%s]异常:%s", charsetName, e.getMessage()));
		}
		if (strLen == length) {
			return str;
		} else if (strLen < length) {
			int temp = length - strLen;
			StringBuffer sb = new StringBuffer(length);
			sb.append(str);
			// String tem = "";
			for (int i = 0; i < temp; i++) {
				// tem = tem + " ";
				sb.append(" ");
			}
			// return str + tem;
			return sb.toString();
		} else {
			for (int i = 0; i < length; i++) {
				try {
					returnByte[i] = str.getBytes(charsetName)[i];
				} catch (UnsupportedEncodingException e) {
					System.err.println(String.format("不支持的编码[%s]异常:%s", charsetName, e.getMessage()));
				}
			}
			String returnStr = null;
			try {
				returnStr = new String(returnByte, charsetName);
			} catch (UnsupportedEncodingException e) {
				System.err.println(String.format("不支持的编码[%s]异常:%s", charsetName, e.getMessage()));
			}
			return returnStr;
			// return str.substring(0,length - 1);
		}
	}
	/**
	 * 数字转字符串，位数不够左补0
	 * @author qingfeng.xiu
	 */
	public static String formatNum(String str, int length) {
		String charsetName = "GBK";
		int strLen = 0;
		try {
			strLen = str.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
			System.err.println(String.format("不支持的编码[%s]异常:%s", charsetName, e.getMessage()));
		}
		if (strLen == length) {
			return str;
		} else if (strLen < length) {
			int temp = length - strLen;
			StringBuffer sb = new StringBuffer(length);
			// String tem = "";
			for (int i = 0; i < temp; i++) {
				sb.append("0");
				// tem = "0" + tem;
			}
			sb.append(str);
			// return tem + str;
			return sb.toString();
		} else {
			return str.substring(0, length);
		}
	}
	/**
	 * 数字转字符串
	 * @param val
	 * @param length
	 * @return
	 */
	public static String formatInt(int val, int length) {
		String str = null;
		if (val == 0) {
			//给空格不做修改
			str ="";
			for (int i=0;i<length;i++) {
				str += " ";
			}
		} else {
			str = formatNum(String.valueOf(val), length);;
		}
		return str;
		
	}
	

}
