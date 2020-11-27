/**
 * Title: ButtonFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月13日下午4:10:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter11;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @class_name:ButtonFrame2020年1月13日
 * @comments:awt事件的测试，响应按钮的事件组件
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午4:10:07
 */
public class ButtonFrame extends JFrame {
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	/**
	 * @throws HeadlessException
	 */
	public ButtonFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		this.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		//crate Button
		JButton yelloButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("Red");
		buttonPanel = new JPanel();
		//add buttonms to panel
		buttonPanel.add(yelloButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(redButton);
		//add panel to frame
		this.add(buttonPanel);
		//crate button actions
		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction redAction = new ColorAction(Color.RED);
		//associate actions with buttons
		yelloButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
	}

	/**
	 * @param arg0
	 */
	public ButtonFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public ButtonFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ButtonFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月13日下午4:10:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new ButtonFrame();
		frame.setTitle("ButtonTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	/**
	 * 
	 * @class_name:ColorAction2020年1月13日
	 * @comments: 时间响方法
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年1月13日下午4:13:05
	 */
	class ColorAction implements ActionListener
	{
		private Color backgroundColor;
		/**
		 * 
		 * @param c
		 */
		public ColorAction(Color c){
			backgroundColor = c;
		}
		/**
		 *  事件响应方法
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			buttonPanel.setBackground(backgroundColor);
		}
		
	}
}
