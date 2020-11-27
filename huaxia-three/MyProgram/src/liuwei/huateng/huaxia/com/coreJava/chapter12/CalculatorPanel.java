/**
 * Title: CalculatorPanel.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月14日上午11:07:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter12;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import liuwei.huateng.huaxia.com.coreJava.chapter11.MouseComponent;

/**
 * @class_name:CalculatorPanel2020年1月14日
 * @comments: 计算器的计算类
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月14日上午11:07:32
 */
public class CalculatorPanel extends JPanel {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private JButton display ;
	private JPanel panel;
	private double result;
	private String lastCommand;
	private boolean start;
	/**
	 * 
	 */
	public CalculatorPanel() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		result = 0;
		lastCommand = "=";
		start = true;
		//add the display
		display = new JButton("0");
		display.setEnabled(false);
		this.add(display,BorderLayout.NORTH);
		
		ActionListener insert =new InsertAction();
		ActionListener command = new CommandAction();
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,4));	
		
		this.addButton("7", insert);
		this.addButton("8", insert);
		this.addButton("9", insert);
		this.addButton("/", command);
		
		this.addButton("4", insert);
		this.addButton("5", insert);
		this.addButton("6", insert);
		this.addButton("*", command);
		
		this.addButton("1", insert);
		this.addButton("2", insert);
		this.addButton("3", insert);
		this.addButton("-", command);
		
		this.addButton("0", insert);
		this.addButton(".", insert);
		this.addButton("=", command);
		this.addButton("+", command);
		this.add(panel,BorderLayout.CENTER);
	}

	/**
	 * @param arg0
	 */
	public CalculatorPanel(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public CalculatorPanel(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CalculatorPanel(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: addButton
	 *@Description: TODO
	 *添加按钮
	 *@param label
	 *@param listener
	 *@author: LiuWei
	 *@Date: 2020年1月14日上午11:15:10
	 */
	private void addButton(String label,ActionListener listener){
		JButton button = new JButton(label);
		button.addActionListener(listener);
		panel.add(button);
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月14日上午11:07:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.add(new CalculatorPanel());
		frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		frame.setTitle("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	/**
	 * 
	 * @class_name:InsertAction2020年1月14日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年1月14日上午11:13:10
	 */
	private class InsertAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String input = arg0.getActionCommand();
			if(start)
			{
				display.setText("");
				start = false;
			}
			display.setText(display.getText()+input);
		}
	}
	/**
	 * 
	 * @class_name:CommandAction2020年1月14日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年1月14日上午11:13:49
	 */
	private class CommandAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String command = arg0.getActionCommand();
			if(start)
			{
				if(command.equals("-"))
				{
					display.setText(command);
					start = false;
				}
				else lastCommand = command;
			} else
			{
				calculage(Double.parseDouble(display.getText()));
				lastCommand = command;
				start = true;
			}
		}
	}
	/**
	 * @Title: calculage
	 *@Description: TODO
	 *计算方法
	 *@param x
	 *@author: LiuWei
	 *@Date: 2020年1月14日下午1:54:59
	 */
	public void calculage(double x)
	{
		if(lastCommand.equals("+"))
			result += x;
		else if(lastCommand.equals("-"))
			result -=x;
		else if(lastCommand.equals("*"))
			result *=x;
		else if(lastCommand.equals("/"))
			result /=x;
		else if(lastCommand.equals("="))
			result =x;
		display.setText(""+result);
	}
}
