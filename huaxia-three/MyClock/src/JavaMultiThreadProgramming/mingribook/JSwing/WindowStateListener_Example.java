/**
 * Title: WindowStateListener_Example.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月17日上午11:24:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JSwing;

import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

/**
 * @class_name:WindowStateListener_Example2019年10月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月17日上午11:24:35
 */
public class WindowStateListener_Example extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public WindowStateListener_Example() throws HeadlessException {
		super();
		this.setTitle("捕获窗体状态事件");
		this.setBounds(100, 100, 500, 375);
		this.addWindowStateListener(new MyWindowStateListener());;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private class MyWindowStateListener implements WindowStateListener {

		@Override
		public void windowStateChanged(WindowEvent e) {
			// TODO Auto-generated method stub
			int oldState = e.getOldState();
			int newState = e.getNewState();
			String from = "";
			String to = "";
			switch (oldState) {
			case Frame.NORMAL:
				from = "正常化";
				break;
			case Frame.MAXIMIZED_BOTH:
				from = "最大化";
				break;
			default:
				from = "最小化";
			}
			switch (newState) {
			case Frame.NORMAL:
				to = "正常化";
				break;
			case Frame.MAXIMIZED_BOTH:
				to = "最大化";
				break;
			default:
				to = "最小化";
			}
			System.out.println(from+"------>"+to);
		}

	}

	/**
	 * @param arg0
	 */
	public WindowStateListener_Example(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public WindowStateListener_Example(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public WindowStateListener_Example(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 * @author: LiuWei
	 * @Date: 2019年10月17日上午11:24:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WindowStateListener_Example frame = new WindowStateListener_Example();
		frame.setVisible(true);
	}

}
