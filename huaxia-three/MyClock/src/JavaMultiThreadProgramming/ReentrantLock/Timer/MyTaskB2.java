package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Date;
import java.util.TimerTask;

public class MyTaskB2 extends TimerTask{
	@Override
	public void run(){
		System.out.println("B run timer="+new Date());
	}
}

