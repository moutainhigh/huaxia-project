/**
 * Title: InnerClassTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��31������11:10:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter6;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * @class_name:InnerClassTest2019��12��31��
 * @comments:�����ڲ����ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��31������11:10:53
 */
public class InnerClassTest {

	/**
	 * 
	 */
	public InnerClassTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��31������11:10:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TalkingClock clock = new TalkingClock(1000,true);
		clock.start();
		JOptionPane.showMessageDialog(null,"Quit program?");
		System.exit(0);
	}
	
}
class TalkingClock
{
	private int interval;
	private boolean beep;
	public TalkingClock(int interval,boolean beep)
	{
		this.interval = interval;
		this.beep = beep;
	}
	/**
	 * @Title: start
	 *@Description: TODO������ʱ��
	 *@author: LiuWei
	 *@Date: 2019��12��31������11:13:55
	 */
	public void start()
	{
		ActionListener listener = new TimePrinter();
		Timer t = new Timer(interval,listener);
		t.start();
	}
	public class TimePrinter implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("At the tone,the time is"+new Date());
			if(beep)
				Toolkit.getDefaultToolkit().beep();
		}
		
	}

}