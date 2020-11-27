/**
 * Title: SimpleEvent.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日上午10:29:28
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JFrame;

import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @class_name:SimpleEvent2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日上午10:29:28
 */
public class SimpleEvent extends JFrame {
	private JButton jb = new JButton("我是按钮，单击我");
	/**
	 * @throws HeadlessException
	 */
	public SimpleEvent() throws HeadlessException {
		// TODO Auto-generated constructor stub
		setLayout(null);
		this.setTitle("定义自动换行的文本域");
		Container c = this.getContentPane();
		c.add(jb);
		jb.setBounds(10,10,100,30);
		jb.addActionListener(new jbAction());
		this.setSize(200,100);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * @param arg0
	 */
	public SimpleEvent(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public SimpleEvent(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SimpleEvent(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日上午10:29:28
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SimpleEvent();
	}
	/**
	 * 
	 * @class_name:jbAction2019年10月15日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年10月15日上午10:34:34
	 */
	class jbAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			jb.setText("我被单击了");
		}
	}
}
