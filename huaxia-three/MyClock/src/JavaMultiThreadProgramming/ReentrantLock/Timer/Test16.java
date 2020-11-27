package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
/**
 * scheduleAtFixedRate具有追赶性，追赶过期任务的完成。
 * @author liuwei
 *
 */
public class Test16 {
	public static void main(String args[]){
		MyTaskB task1 = new MyTaskB();
		System.out.println("当前时间是："+new Date());
		Calendar calendarRef = Calendar.getInstance();
		calendarRef.set(Calendar.SECOND, calendarRef.get(Calendar.SECOND)-20);
		Date runDate = calendarRef.getTime();
		Timer timer1 = new Timer();
		timer1.scheduleAtFixedRate(task1,runDate,2000);
	}
}