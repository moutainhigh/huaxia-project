package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
/**
 * 提前运行，直接运行
 * @author liuwei
 *
 */
public class Test3 {
	public static void main(String args[]){
		System.out.println("当前时间是："+new Date());
		Calendar calendarRef = Calendar.getInstance();
		calendarRef.set(Calendar.SECOND, calendarRef.get(Calendar.SECOND)-10);
		Date runDate = calendarRef.getTime();
		System.out.println("计划时间为"+runDate);
		MyTask task = new MyTask();
		Timer timer = new Timer();//设置守护线程
		timer.schedule(task, runDate);
	}
}
