/**
 * Title: Bounce.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月21日上午10:09:25
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @class_name:Bounce2020年8月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月21日上午10:09:25
 */
public class BounceThread {

	/**
	 * Constructor
	 */
	public BounceThread() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月21日上午10:09:25
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new BounceFrame2();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
/**
 * 
 * @class_name:BounceFrame2020年8月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月21日上午10:10:00
 */
class BounceFrame2 extends JFrame{
	private BallComponent comp;
	public static final int STEP = 1000;
	public static final int DELAY = 3;
	public BounceFrame2()
	{
		setTitle("Bounce");
		comp = new BallComponent();
		add(comp,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel,"Start",new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addBall();
			}
		});
		addButton( buttonPanel,"Close",new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		add(buttonPanel,BorderLayout.SOUTH);
		pack();
	}
	/**
	 * @Title: addButton
	 *@Description: TODO
	 *@param c
	 *@param title
	 *@param listener
	 *@author: LiuWei
	 *@Date: 2020年8月21日上午10:18:27
	 */
	public void addButton(Container c,String title,ActionListener listener){
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	/**
	 * @Title: addBall
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年8月21日上午10:28:10
	 */
	public void addBall(){
		Ball ball  = new Ball();
		comp.add(ball);
		Thread t = new ballClass(ball);
		t.start();
	}
	class ballClass extends Thread{
		Ball ball = null;
		public ballClass(Ball ball){
			this.ball = ball;
		}
		public void run(){
			for(int i= 1;i<=STEP;i++){
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
