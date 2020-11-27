package Algorithm;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 定时任务,让计算机进行定时想起来
 * @author liuwei
 */
public class TimerTest {
	public static void main(String args[]){
		final Timer timer = new Timer();
		final TimerTask timerTask = new TimerTask(){
			int count =3;//任务执行三次
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				System.out.println("beep...");
				if(--count<=0){
					System.out.println("timer cancele d. ");
					this.cancel();//取消任务
					timer.cancel();//取消计时器
				}
			}
		};
		//设定计时器，100毫秒后启动计时器，每隔1000毫秒执行一次
		timer.schedule(timerTask, 100,1000);
	}
}
