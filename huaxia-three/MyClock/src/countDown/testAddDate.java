package countDown;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class testAddDate {

	public static void main(String args[]){
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss SSS毫秒");
		Calendar c = Calendar.getInstance();//创建当前的一个时间对象
//		System.out.println(Calendar.YEAR);
//		System.out.println(Calendar.MONTH);
//		System.out.println(Calendar.DAY_OF_MONTH);
//		System.out.println(Calendar.HOUR_OF_DAY);
//		System.out.println( Calendar.MINUTE);
//		System.out.println( Calendar.MINUTE);
		for(int i=0 ;i<100;i++){
			c.set(Calendar.YEAR,c.get(Calendar.YEAR));
			c.set(Calendar.MONTH, c.get(Calendar.MONTH));
			c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)+1);
			c.set(Calendar.HOUR_OF_DAY,c.get(Calendar.HOUR_OF_DAY));
			c.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
			c.set(Calendar.SECOND,c.get(Calendar.SECOND));
			c.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND));
			System.out.println("现在时间是： "+format.format(c.getTime()));
		}
	}
}
