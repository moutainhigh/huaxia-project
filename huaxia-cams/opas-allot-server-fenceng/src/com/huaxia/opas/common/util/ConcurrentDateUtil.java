package com.huaxia.opas.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 时间格式
 * @author 
 *
 */
public class ConcurrentDateUtil {
	
	private static ThreadLocal<DateFormat> threadLocal=new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("HH:mm");
		}
	};

	public static Date parse(String dateStr) throws ParseException{
		return threadLocal.get().parse(dateStr);
	}
	
	public static String format(Date date) throws ParseException{
		return threadLocal.get().format(date);
	}
}
