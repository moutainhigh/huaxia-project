/**
 * Title: JPanelTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日上午9:45:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @class_name:JPanelTest2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日上午9:45:47
 */
public class JPanelTest extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public JPanelTest() throws HeadlessException {
		// TODO Auto-generated constructor stub
		Container c = this.getContentPane();
		this.setLayout(new GridLayout(2,1,10,10));
		JPanel p1 = new JPanel(new GridLayout(1,3,10,10));
		JPanel p2 = new JPanel(new GridLayout(1,2,10,10));
		JPanel p3 = new JPanel(new GridLayout(1,2,10,10));
		JPanel p4 = new JPanel(new GridLayout(2,1,10,10));
		p1.add(new JButton("1"));
		p1.add(new JButton("2"));
		p1.add(new JButton("3"));
		p2.add(new JButton("4"));
		p2.add(new JButton("5"));
		p3.add(new JButton("6"));
		p3.add(new JButton("7"));
		p4.add(new JButton("8"));
		p4.add(new JButton("9"));
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p4);
		this.setSize(300,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * @param arg0
	 */
	public JPanelTest(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public JPanelTest(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public JPanelTest(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日上午9:45:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JPanelTest();
	}

}
