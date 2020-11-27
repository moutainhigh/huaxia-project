package Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTime24 {

	public TestTime24() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String args[]){
		System.out.println(getNowTimeHHmm());
	}
	/**
	 * 返回当前时间12:22
	 */
	public static String getNowTimeHHmm() {
		long t = System.currentTimeMillis();
		Date date = new Date(t);
		SimpleDateFormat sdf = new SimpleDateFormat("dd HH:mm");
		String timeReq = sdf.format(date);
		return timeReq;
	}
}
