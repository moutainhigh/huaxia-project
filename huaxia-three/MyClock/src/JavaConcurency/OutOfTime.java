package JavaConcurency;

import java.util.Timer;
import java.util.TimerTask;
/**
 * 测试一些定时器的运行
 * @author liuwei
 */
public class OutOfTime {
	public static void main(String args[]){
		Timer timer = new Timer();
		 while(true)
		try{
			timer.schedule(new ThrowTask(), 1);
			Thread.sleep(1);
			timer.schedule(new ThrowTask(), 1);
			Thread.sleep(5);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	static class ThrowTask extends TimerTask{
		public void run(){
			throw new RuntimeException();
		}
	}
}
