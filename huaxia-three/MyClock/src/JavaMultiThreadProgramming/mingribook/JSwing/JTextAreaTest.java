/**
 * Title: JTextAreaTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月17日下午4:31:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JSwing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * @class_name:JTextAreaTest2019年10月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月17日下午4:31:31
 */
public class JTextAreaTest extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public JTextAreaTest() throws HeadlessException {
		// TODO Auto-generated constructor stub
				super();
				this.setTitle("定义自动换行的文本域");
				this.setSize(200,100);
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Container cp = this.getContentPane();
				JTextArea jt = new JTextArea("文本域",6,6);
				jt.setLineWrap(true);
				this.setBounds(10,10,500,500);;
				this.setVisible(true);
				cp.add(jt,BorderLayout.CENTER);
	}

	/**
	 * @param arg0
	 */
	public JTextAreaTest(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public JTextAreaTest(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public JTextAreaTest(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月17日下午4:31:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JTextAreaTest();
	}

}
