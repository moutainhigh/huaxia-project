/**
 * Title: JoinTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月21日上午11:26:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Threa;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * @class_name:JoinTest2019年10月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月21日上午11:26:46
 */
public class JoinTest extends JFrame {
	private Thread threadA ;
	private Thread threadB;
	final JProgressBar progressBar = new JProgressBar();
	final JProgressBar progressBar2 = new JProgressBar();
	int count = 0;
	/**
	 * @throws HeadlessException
	 */
	public JoinTest() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		getContentPane().add(progressBar,BorderLayout.NORTH);
		getContentPane().add(progressBar2,BorderLayout.SOUTH);
		progressBar.setStringPainted(true);
		progressBar2.setStringPainted(true);
		threadA = new Thread(new Runnable(){
			int count = 0;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					progressBar.setValue(++count);
					try {
						Thread.sleep(100);
						threadB.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		threadA.start();
		threadB = new Thread(new Runnable(){
			int count = 0;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					progressBar2.setValue(++count);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(count == 100) break;
				}
			}
		});
		threadB.start();
	}
	/**
	 * @Title: init
	 *@Description: TODO
	 *@param frame
	 *@param width
	 *@param height
	 *@author: LiuWei
	 *@Date: 2019年10月21日下午1:48:46
	 */
	public static void init(JFrame frame,int width,int height){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width,height);
		frame.setVisible(true);
	}
	/**
	 * @param arg0
	 */
	public JoinTest(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public JoinTest(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public JoinTest(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月21日上午11:26:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init(new JoinTest(),100,100);
	}

}
