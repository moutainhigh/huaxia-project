/**
 * Title: BorderFrame.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月14日下午4:01:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

/**
 * @class_name:BorderFrame2020年1月14日
 * @comments: 
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月14日下午4:01:56
 */
public class BorderFrame extends JFrame {
	private JPanel demoPanel;
	private JPanel buttonPanel;
	private ButtonGroup group;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	/**
	 * @throws HeadlessException
	 */
	public BorderFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		demoPanel = new JPanel();
		buttonPanel = new JPanel();
		group = new ButtonGroup();
		addRadioButton("Lowered bevel",BorderFactory.createLoweredBevelBorder());
		addRadioButton("Raised bevel",BorderFactory.createRaisedBevelBorder());
		addRadioButton("Etched",BorderFactory.createEtchedBorder());
		addRadioButton("Line",BorderFactory.createLineBorder(Color.BLUE));
		addRadioButton("Matte",BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
		addRadioButton("Empty",BorderFactory.createEmptyBorder());
		Border etched = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(etched,"Border types");
		buttonPanel.setBorder(titled);
		setLayout(new GridLayout(2,1));
		add(buttonPanel);
		add(demoPanel);
		pack();
	}
	/**
	 * @Title: addRadioButton
	 *@Description: TODO 添加安轩按钮
	 *@param buttonName
	 *@param b
	 *@author: LiuWei
	 *@Date: 2020年1月14日下午4:03:56
	 */
	public void addRadioButton(String buttonName,final Border b)
	{
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				demoPanel.setBorder(b);
			}
			
		});
		group.add(button);
		buttonPanel.add(button);
	}
	/**
	 * @param arg0
	 */
	public BorderFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public BorderFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BorderFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月14日下午4:01:56
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new BorderFrame();
//		frame.add(new CalculatorPanel());
		frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		frame.setTitle("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
