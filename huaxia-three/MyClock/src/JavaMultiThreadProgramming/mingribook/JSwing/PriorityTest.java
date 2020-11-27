/**
 * Title: PriorityTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月17日下午4:54:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.JSwing;

import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * @class_name:PriorityTest2019年10月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月17日下午4:54:49
 */
public class PriorityTest extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public PriorityTest() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		this.setTitle("进度条");
		this.setSize(100,100);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(4,1));
		JProgressBar progressBar = new JProgressBar();
		JProgressBar progressBar2 = new JProgressBar();
		JProgressBar progressBar3 = new JProgressBar();
		JProgressBar progressBar4 = new JProgressBar();
		Thread threadA = new Thread(new MyThread(progressBar));
		Thread threadB = new Thread(new MyThread(progressBar2));
		Thread threadC = new Thread(new MyThread(progressBar3));
		Thread threadD = new Thread(new MyThread(progressBar4));
		setPriority("threadA",5,threadA);
		setPriority("threadB",5,threadB);
		setPriority("threadC",4,threadC);
		setPriority("threadD",3,threadD);
		Container cp = this.getContentPane();
		this.setBounds(10,10,500,500);;
		this.setVisible(true);
		cp.add(progressBar);
		cp.add(progressBar2);
		cp.add(progressBar3);
		cp.add(progressBar4);
	}
	/**
	 * @Title: setPriority
	 *@Description: TODO
	 *@param threadName
	 *@param priority
	 *@param t
	 *@author: LiuWei
	 *@Date: 2019年10月18日上午9:43:00
	 */
	public static void setPriority(String threadName,int priority,Thread t){
		t.setPriority(priority);
		t.setName(threadName);
		t.start();
	}
	/**
	 * @param arg0
	 */
	public PriorityTest(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public PriorityTest(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public PriorityTest(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月17日下午4:54:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PriorityTest();
	}
	/**
	 * 
	 * @class_name:MyThread2019年10月18日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年10月18日上午9:47:17
	 */
	private final class MyThread implements Runnable{
		private final JProgressBar bar;
		int count;
		private MyThread(JProgressBar bar){
			this.bar = bar;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				bar.setValue(count+=10);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("当前线程被中断");
				}
			}
		}
		
	}
}
