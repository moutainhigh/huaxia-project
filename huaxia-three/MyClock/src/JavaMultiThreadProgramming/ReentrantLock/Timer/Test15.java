package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Test15 {
	public static void main(String args[]){
		MyTaskA6 task1 = new MyTaskA6();
		System.out.println("当前时间是："+new Date());
		Calendar calendarRef = Calendar.getInstance();
		Date runDate = calendarRef.getTime();
		Timer timer1 = new Timer();
		timer1.schedule(task1,runDate,2000);
	}
}