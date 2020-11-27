/**
 * Title: TimerTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��21������3:10:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
/**
 * @class_name:TimerTest2020��9��21��
 * @comments:TimerTask ��ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��21������3:10:55
 */
public class TimerTest {
	/**
	 * Constructor
	 */
	public TimerTest() {
		// TODO Auto-generated constructor stub
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��21������3:10:55
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Timer  timer = new Timer();
		final TimerTask timerTask = new TimerTask(){
			int count = 3;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				System.out.println("beep.....");
				if(--count<=0){
					System.out.println("timer canceled.");
					this.cancel();
					timer.cancel();
				}
			}
		};
		timer.schedule(timerTask, 100,1000);
	}

}
