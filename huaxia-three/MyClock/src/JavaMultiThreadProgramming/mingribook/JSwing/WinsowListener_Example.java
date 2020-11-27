/**
 * Title: WinsowListener_Example.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月17日下午2:15:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JSwing;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * @class_name:WinsowListener_Example2019年10月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月17日下午2:15:03
 */
public class WinsowListener_Example extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public WinsowListener_Example() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		this.setTitle("捕获其他窗体状态事件");
		this.setBounds(100, 100, 500, 375);
		this.addWindowListener(new MyWindowListener());;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * @param arg0
	 */
	public WinsowListener_Example(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public WinsowListener_Example(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public WinsowListener_Example(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月17日下午2:15:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WinsowListener_Example frame = new WinsowListener_Example();
		frame.setVisible(true);
	}
	private class MyWindowListener implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("窗口被激活");
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("窗口已经被关闭");
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("窗口将要被关闭");
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("窗口不再处于激活状态");
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("窗口被非最小化");
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("窗口被打开");
		}
		
	}
}
