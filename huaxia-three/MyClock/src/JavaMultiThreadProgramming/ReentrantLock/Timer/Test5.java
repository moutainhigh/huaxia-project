package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
/**
 * 多个任务.同一个timer时间是进行累计的。
 * @author liuwei
 *
 */
public class Test5 {
	public static void main(String args[]){
		System.out.println("当前时间是："+new Date());
		Calendar calendarRef = Calendar.getInstance();
		Date runDate1 = calendarRef.getTime();
		System.out.println("A计划时间为"+runDate1);
		
		Calendar calendarRef2 = Calendar.getInstance();
		calendarRef2.add(Calendar.SECOND, 10);
		Date runDate2 = calendarRef2.getTime();
		System.out.println("B计划时间为"+runDate2);
		
		MyTaskA task1 = new MyTaskA();
		MyTaskB task2 = new MyTaskB();
		Timer timer1 = new Timer();
		timer1.schedule(task1, runDate1);
		timer1.schedule(task2, runDate2);
	}
}
