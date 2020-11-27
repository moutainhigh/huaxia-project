package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Test10 {
	public static void main(String args[]){
		System.out.println("当前时间是："+new Date());
		Calendar calendarRef = Calendar.getInstance();
		Date runDate1 = calendarRef.getTime();
		System.out.println("计划执行时间为"+runDate1);
		MyTaskA4 task1 = new MyTaskA4();
		MyTaskB2 task2 = new MyTaskB2();
		Timer timer1 = new Timer();
		timer1.schedule(task1, runDate1,2000);
		timer1.schedule(task2, runDate1,2000);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timer1.cancel();
	}
}