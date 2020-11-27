/**
 * Title: BallComponent.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月17日下午2:27:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter14;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * @class_name:BallComponent2020年1月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月17日下午2:27:08
 */
public class BallComponent extends JPanel {
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 350;
	private java.util.List<Ball> balls = new ArrayList<>();
	/**
	 * 
	 */
	public BallComponent() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *添加ball
	 *@param b
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午3:01:27
	 */
	public void add(Ball b)
	{
		balls.add(b);
	}
	/**
	 * 
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(Ball b :balls)
		{
			g2.fill(b.getShape());
		}
	}
	/**
	 * 获取大小
	 */
	public Dimension getPreferredSize()
	{
		return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	/**
	 * @param arg0
	 */
	public BallComponent(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public BallComponent(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BallComponent(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午2:27:08
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
