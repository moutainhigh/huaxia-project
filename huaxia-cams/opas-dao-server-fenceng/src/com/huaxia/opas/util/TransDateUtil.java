package com.huaxia.opas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransDateUtil {
	public static Date String2Date(String timeStr, String parse) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat(parse);
		return df.parse(timeStr);
	}
	public static String Date2String(Date date, String parse) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat(parse);
		return df.format(date);
	}
}
