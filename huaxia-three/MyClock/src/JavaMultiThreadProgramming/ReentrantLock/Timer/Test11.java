package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Test11 {
	public static void main(String args[]){
		MyTaskA4 task1 = new MyTaskA4();
		System.out.println("当前时间是："+new Date());
		Timer timer1 = new Timer();
		timer1.schedule(task1,2000);
	}
}