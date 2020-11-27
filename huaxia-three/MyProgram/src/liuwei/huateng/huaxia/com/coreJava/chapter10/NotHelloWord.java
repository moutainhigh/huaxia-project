/**
 * Title: NotHelloWord.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月13日上午11:28:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter10;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @class_name:NotHelloWord2020年1月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日上午11:28:38
 */
public class NotHelloWord {

	/**
	 * 
	 */
	public NotHelloWord() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月13日上午11:28:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new NotHelloWordFrame();
		frame.setTitle("NotHelloWorld");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
/**
 * 
 * @class_name:NotHelloWordFrame2020年1月13日
 * @comments: 窗体类
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午2:52:08
 */
class NotHelloWordFrame extends JFrame
{
	public NotHelloWordFrame(){
		add(new NotHelloWorldComponent());
		pack();
	}
}
/**
 * 
 * @class_name:NotHelloWorldComponent2020年1月13日
 * @comments: 组件类
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午2:52:11
 */
class NotHelloWorldComponent extends JComponent
{
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	/**
	 * @Title: paintCompanent
	 *@Description: TODO
	 *@param g
	 *@author: LiuWei
	 *@Date: 2020年1月13日下午2:49:54
//	 */
//	public NotHelloWorldComponent(){
//		;
//		paintCompanent( this.getComponentGraphics(getGraphics()));
//	}
	public void paintComponent(Graphics g)
	{
		g.drawString("Not a hello ,world program", MESSAGE_X, MESSAGE_Y);
	}
	/**
	 * 
	 */
	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
}