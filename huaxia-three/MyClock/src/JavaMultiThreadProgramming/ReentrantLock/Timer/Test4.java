package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
/**
 * 多个任务
 * @author liuwei
 *
 */
public class Test4 {
	public static void main(String args[]){
		System.out.println("当前时间是："+new Date());
		Calendar calendarRef = Calendar.getInstance();
		calendarRef.add(Calendar.SECOND, 10);
		Date runDate1 = calendarRef.getTime();
		System.out.println("计划时间为"+runDate1);
		
		Calendar calendarRef2 = Calendar.getInstance();
		calendarRef2.add(Calendar.SECOND, 15);
		Date runDate2 = calendarRef2.getTime();
		System.out.println("计划时间为"+runDate2);
		
		MyTask task1 = new MyTask();
		Timer timer1 = new Timer();
		timer1.schedule(task1, runDate1);
		
		MyTask task2 = new MyTask();
		Timer timer2 = new Timer();
		timer2.schedule(task2, runDate2);
	}
}
