package JavaMultiThreadProgramming.mingribook.javaStruct;

import java.util.Date;

/**
 * 日期格式化
 * @author liuwei
 *
 */
public class GetDate {
	public static void main(String args[]){
		Date date = new Date();
		String hour = String.format("%tH", date);
		String minute = String.format("%tM", date);
		String second = String.format("%tS", date);
		System.out.println("今年是:"+hour+"时");
		System.out.println("现在是:"+minute+"分");
		System.out.println("今天是:"+second+"秒");

	}
}
