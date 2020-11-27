package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Test14 {
	public static void main(String args[]){
		MyTaskA5 task1 = new MyTaskA5();
		System.out.println("当前时间是："+new Date());
		Timer timer1 = new Timer();
		timer1.schedule(task1,3000,3000);
	}
}