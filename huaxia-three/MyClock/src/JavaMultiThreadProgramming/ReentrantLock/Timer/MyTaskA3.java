package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Date;
import java.util.TimerTask;

public class MyTaskA3 extends TimerTask{
	@Override
	public void run(){
		System.out.println("A run timer="+new Date());
		this.cancel();
		System.out.println("A任务自己移除自己");

	}
}

