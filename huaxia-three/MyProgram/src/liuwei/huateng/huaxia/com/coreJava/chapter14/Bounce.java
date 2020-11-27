/**
 * Title: Bounce.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月17日下午2:25:28
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter14;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import liuwei.huateng.huaxia.com.coreJava.chapter12.DataExchangeFrame;


/**
 * @class_name:Bounce2020年1月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月17日下午2:25:28
 */
public class Bounce {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	/**
	 * 
	 */
	public Bounce() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午2:25:28
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new BounceFrame();
//		frame.add(new CalculatorPanel());
		frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		frame.setTitle("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
/**
 * 
 * @class_name:BounceFrame2020年1月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月17日下午3:05:42
 */
class BounceFrame extends JFrame
{
	private BallComponent comp;
	public static final int STEPS= 1000;
	public static final int DELAY = 3;
	public BounceFrame()
	{
		this.setTitle("Bounce");
		comp = new BallComponent();
		this.add(comp,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel,"Start",new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addBall();
			}
			
		});
		addButton(buttonPanel,"Close",new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		this.add(buttonPanel,BorderLayout.SOUTH);
		pack();
	}
	/**
	 * @Title: addButton
	 *@Description: TODO
	 *@param 添加按钮
	 *@param title
	 *@param listener
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午2:41:30
	 */
	public void addButton(Container c,String title ,ActionListener listener)
	{
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	/**
	 * @Title: addBall
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午3:05:36
	 */
	public void addBall()
	{
		try{
			Ball ball = new Ball();
			comp.add(ball);
			for(int i=1;i<STEPS;i++)
			{
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				Thread.sleep(DELAY);
			}
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}