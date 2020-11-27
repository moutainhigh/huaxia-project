/**
 * Title: TimerTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月31日上午10:34:46
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
 * @class_name:TimerTest2019年12月31日
 * @comments:测试定时器的运行
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日上午10:34:46
 */
public class TimerTest {

	/**
	 * 
	 */
	public TimerTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月31日上午10:34:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ActionListener listener = new TimePrinter();
		Timer t = new Timer(100,listener);
		t.start();
		JOptionPane.showMessageDialog(null,"Quit program?");
		System.exit(0);
	}

}
class TimePrinter implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("At the tone,the time is"+new Date());
		Toolkit.getDefaultToolkit().beep();
	}
	
}
