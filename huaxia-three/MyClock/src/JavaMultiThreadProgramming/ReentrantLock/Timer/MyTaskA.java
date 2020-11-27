package JavaMultiThreadProgramming.ReentrantLock.Timer;

import java.util.Date;
import java.util.TimerTask;

public class MyTaskA extends TimerTask{
	@Override
	public void run(){
		System.out.println("A begin timer="+new Date());
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("A end timer="+new Date());

	}
}

