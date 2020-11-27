/**
 * Title: RadioButtonFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月14日下午3:47:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @class_name:RadioButtonFrame2020年1月14日
 * @comments: 单选按钮的测试，动态改变字体的大小
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月14日下午3:47:29
 */
public class RadioButtonFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private JPanel buttonPanel;
	private ButtonGroup group;
	private JLabel label;
	private static final int DEFAULT_SIZE = 36;
	/**
	 * @throws HeadlessException
	 */
	public RadioButtonFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif",Font.PLAIN,DEFAULT_SIZE));
		this.add(label,BorderLayout.CENTER);
		//add the radio buttons
		buttonPanel = new JPanel();
		group = new ButtonGroup();
		addRadioButton("Small",8);
		addRadioButton("Medium",12);
		addRadioButton("Large",18);
		addRadioButton("Extra large",36);
		this.add(buttonPanel,BorderLayout.SOUTH);
		pack();
	}
	/**
	 * @Title: addRadioButton
	 *@Description: TODO
	 *@param name
	 *@param size
	 *@author: LiuWei
	 *@Date: 2020年1月14日下午3:56:07
	 */
	public void addRadioButton(String name,final int size)
	{
		boolean selected = size == DEFAULT_SIZE;
		JRadioButton button = new JRadioButton(name,selected);
		group.add(button);
		buttonPanel.add(button);
		//this lisener sets the label font size
		ActionListener lstener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				label.setFont(new Font("Serif",Font.PLAIN,size));
			}
			
		};
		button.addActionListener(lstener);
	}
	/**
	 * @param arg0
	 */
	public RadioButtonFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public RadioButtonFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public RadioButtonFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月14日下午3:47:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new RadioButtonFrame();
//		frame.add(new CalculatorPanel());
		frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		frame.setTitle("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
