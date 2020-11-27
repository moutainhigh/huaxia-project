package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
/**
 * 多个任务.任务取消
 * @author liuwei
 *
 */
public class Test9 {
	public static void main(String args[]){
		System.out.println("当前时间是："+new Date());
		Calendar calendarRef = Calendar.getInstance();
		Date runDate1 = calendarRef.getTime();
		System.out.println("计划执行时间为"+runDate1);
		MyTaskA3 task1 = new MyTaskA3();
		MyTaskB2 task2 = new MyTaskB2();
		Timer timer1 = new Timer();
		timer1.schedule(task1, runDate1,4000);
		timer1.schedule(task2, runDate1,4000);
	}
}
