/**
 * Title: FocusEventTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日上午10:40:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * @class_name:FocusEventTest2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日上午10:40:14
 */
public class FocusEventTest extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public FocusEventTest() throws HeadlessException {
		// TODO Auto-generated constructor stub
		JTextField jt = new JTextField("请单击其他文本框",10);
		JTextField jt2 = new JTextField("请单击我",10);
		setLayout(new BorderLayout());
		this.setTitle("焦点测试");
		Container c = this.getContentPane();
		c.add(jt,BorderLayout.NORTH);
		c.add(jt2,BorderLayout.CENTER);
		this.setSize(200,100);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jt.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "文本框失去焦点");
			}
			
		});
	}

	/**
	 * @param arg0
	 */
	public FocusEventTest(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public FocusEventTest(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public FocusEventTest(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日上午10:40:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FocusEventTest();
	}

}
