package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
/**
 * 设置守护线程，主线程运行完之后，守护线程也结束了
 * @author liuwei
 *
 */
public class Test2 {
	public static void main(String args[]){
		System.out.println("当前时间是："+new Date());
		Calendar calendarRef = Calendar.getInstance();
		calendarRef.add(Calendar.SECOND, 10);
		Date runDate = calendarRef.getTime();
		
		MyTask task = new MyTask();
		Timer timer = new Timer(true);//设置守护线程
		timer.schedule(task, runDate);
	}
}
