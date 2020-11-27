/**
 * Title: JScrollPaneTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日上午10:16:28
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * @class_name:JScrollPaneTest2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日上午10:16:28
 */
public class JScrollPaneTest extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public JScrollPaneTest() throws HeadlessException {
		// TODO Auto-generated constructor stub
		this.setTitle("带滚动条的文字编辑器");
		Container c = this.getContentPane();
		JTextArea ta = new JTextArea(20,50);
		JScrollPane sp = new JScrollPane(ta);
		c.add(sp);
		this.setSize(200,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * @param arg0
	 */
	public JScrollPaneTest(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public JScrollPaneTest(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public JScrollPaneTest(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日上午10:16:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JScrollPaneTest();
	}

}
