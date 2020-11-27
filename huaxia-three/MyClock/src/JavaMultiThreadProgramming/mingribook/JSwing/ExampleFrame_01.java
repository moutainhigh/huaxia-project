/**
 * Title: ExampleFrame_01.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月17日上午11:03:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JSwing;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @class_name:ExampleFrame_012019年10月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月17日上午11:03:24
 */
public class ExampleFrame_01 extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public ExampleFrame_01() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		this.setTitle("创建可以滚动的表格");
		this.setBounds(100,100,240,150);;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] columnName={"A","B"};
		String[][] tableValues = {{"A1","B1"},{"A2","B2"},{"A3","B3"},{"A4","B4"},{"A5","B5"}};
		JTable table = new JTable(tableValues,columnName);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane,BorderLayout.CENTER);
	}

	/**
	 * @param arg0
	 */
	public ExampleFrame_01(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public ExampleFrame_01(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ExampleFrame_01(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月17日上午11:03:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExampleFrame_01 frame = new ExampleFrame_01();
		frame.setVisible(true);
	}

}
