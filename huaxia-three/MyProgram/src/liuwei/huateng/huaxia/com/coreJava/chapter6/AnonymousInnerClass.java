/**
 * Title: AnonymousInnerClass.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月31日下午2:23:46
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
 * @class_name:AnonymousInnerClass2019年12月31日
 * @comments:测试匿名类
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日下午2:23:46
 */
public class AnonymousInnerClass {

	/**
	 * 
	 */
	public AnonymousInnerClass() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午2:23:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TalkingClock2 clock = new TalkingClock2();
		clock.start(1000, true);
		//keep program running until user select "OK"
		JOptionPane.showMessageDialog(null, "Qit program?");
		System.exit(0);
	}

}
/**
 * 
 * @class_name:TalkingClock22019年12月31日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日下午2:45:02
 */
class TalkingClock2
{
	/**
	 * @Title: start
	 *@Description: TODO
	 *@param interval
	 *@param beep
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午2:45:08
	 */
	public void start(int interval,final boolean beep)
	{
		ActionListener listener = new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						System.out.println("At the tone,the time is "+new Date());
						if(beep) Toolkit.getDefaultToolkit().beep();
					}
			
				};
		Timer t = new Timer(interval,listener);
		t.start();
	}
}