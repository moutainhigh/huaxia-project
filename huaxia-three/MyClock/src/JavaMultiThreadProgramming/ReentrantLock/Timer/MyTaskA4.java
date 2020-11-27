package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Date;
import java.util.TimerTask;

public class MyTaskA4 extends TimerTask{
	@Override
	public void run(){
		System.out.println("A run timer="+new Date());
	}
}

