package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Test1 {
	public static void main(String args[]){
		System.out.println("当前时间是："+new Date());
		Calendar calendarRef = Calendar.getInstance();
		calendarRef.add(Calendar.SECOND, 10);
		Date runDate = calendarRef.getTime();
		
		MyTask task = new MyTask();
		Timer timer = new Timer();
		timer.schedule(task, runDate);
	}
}
