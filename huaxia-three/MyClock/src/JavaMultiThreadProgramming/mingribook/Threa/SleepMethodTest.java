/**
 * Title: SleepMethodTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月18日下午4:56:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Threa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.Random;

import javax.swing.JFrame;

/**
 * @class_name:SleepMethodTest2019年10月18日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月18日下午4:56:20
 */
public class SleepMethodTest extends JFrame {
	private Thread t;
	private static Color[] color = {Color.BLACK,Color.BLUE,Color.cyan,Color.green,Color.orange,Color.YELLOW,Color.RED,Color.pink,Color.LIGHT_GRAY};
	private static final Random rand = new Random();
	private static Color getC(){
		return color[rand.nextInt(color.length)];
	}
	/**
	 * @throws HeadlessException
	 */
	public SleepMethodTest() throws HeadlessException {
		// TODO Auto-generated constructor stub
		t = new Thread(new Runnable(){
			int x = 30;
			int y = 50;
			public void run(){
				while(true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Graphics graphics = getGraphics();
					graphics.setColor(getC());
					graphics.drawLine(x, y, 100, y++);
					if(y>80){
						y = 50;
					}
				}
			}
		});
		t.start();
	}
	/**
	 * @Title: init
	 *@Description: TODO
	 *@param frame
	 *@param width
	 *@param height
	 *@author: LiuWei
	 *@Date: 2019年10月21日上午11:16:57
	 */
	public static void init(JFrame frame,int width ,int height){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width,height);
		frame.setVisible(true);
	}
	/**
	 * @param arg0
	 */
	public SleepMethodTest(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public SleepMethodTest(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SleepMethodTest(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月18日下午4:56:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init(new SleepMethodTest(),100,100);
	}

}
