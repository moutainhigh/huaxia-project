package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
/**
 * 多个任务.重复执行的程序
 * @author liuwei
 *
 */
public class Test6 {
	public static void main(String args[]){
		System.out.println("当前时间是："+new Date());
		Calendar calendarRef = Calendar.getInstance();
		calendarRef.add(Calendar.SECOND, 10);
		Date runDate1 = calendarRef.getTime();
		System.out.println("计划执行时间为"+runDate1);
		MyTask task = new MyTask();
		Timer timer1 = new Timer();
		timer1.schedule(task, runDate1,4000);
	}
}
