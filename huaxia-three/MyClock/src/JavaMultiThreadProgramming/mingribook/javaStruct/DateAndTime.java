package JavaMultiThreadProgramming.mingribook.javaStruct;

import java.util.Date;

/**
 * 日期格式化
 * %tc 全部日期和时间信息
 * %tF 年-月-日格式
 * @author liuwei
 *
 */
public class DateAndTime {
	public static void main(String args[]){
		Date date = new Date();
		String time =String.format("%tc", date);
		String form = String.format("%tF", date);
		System.out.println("全部的时间信息是："+time);
		System.out.println("年-月-日格式"+form);
	}
}
