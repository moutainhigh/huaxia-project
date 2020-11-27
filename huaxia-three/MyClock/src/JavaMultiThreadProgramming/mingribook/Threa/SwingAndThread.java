/**
 * Title: SwingAndThread.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月25日上午10:27:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Threa;

import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * @class_name:SwingAndThread2019年10月25日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月25日上午10:27:23
 */
public class SwingAndThread extends JFrame {
	private JLabel jl = new JLabel();
	private static Thread t;
	private int count = 0;
	private Container container = getContentPane();
	/**
	 * @throws HeadlessException
	 */
	public SwingAndThread() throws HeadlessException {
		// TODO Auto-generated constructor stub
		this.setBounds(300,200,250,100);
		container.setLayout(null);
		URL url = SwingAndThread.class.getResource("");
		Icon icon = new ImageIcon(url);
		jl.setIcon(icon);
		jl.setText("Hello World");
		jl.setHorizontalAlignment(SwingConstants.LEFT);
		jl.setBounds(10,10,200,50);
		jl.setOpaque(true);
		t = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(count <= 200){
					jl.setBounds(count,10,200,50);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count+=4;
					if(count >= 200){
						count = 10;
					}
				}
			}
		});
		t.start();
		container.add(jl);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * @param arg0
	 */
	public SwingAndThread(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public SwingAndThread(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SwingAndThread(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月25日上午10:27:23
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SwingAndThread();
	}

}
