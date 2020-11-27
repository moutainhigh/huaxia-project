/**
 * Title: DrawTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月13日下午3:06:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter10;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @class_name:DrawTest2020年1月13日
 * @comments: 测试画图程序，一些绘制图形
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午3:06:07
 */
public class DrawTest {

	/**
	 * 
	 */
	public DrawTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月13日下午3:06:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new  DrawFrame();
		frame.setTitle("DrawTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
/**
 * 
 * @class_name:DrawFrame2020年1月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午3:07:23
 */
class DrawFrame extends JFrame
{
	public DrawFrame(){
		this.add(new DrawComponent());
		pack();
	}
}
/**
 * 
 * @class_name:DrawComponent2020年1月13日
 * @comments:画图组件
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午3:07:58
 */
class DrawComponent extends JComponent
{
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
	/**
	 * 组件的重新绘图的方法，每一次重新绘图就会调用此个方法
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		//draw a rectangle
		double leftX = 100;
		double topY = 100;
		double width = 200;
		double height = 150;
		Rectangle2D rect = new Rectangle2D.Double(leftX,topY,width,height);
		g2.draw(rect);
		//draw the eclosed ellipse
		Ellipse2D ellipse =new Ellipse2D.Double();
		ellipse.setFrame(rect);
		g2.draw(ellipse);
		//draw a diagonal line
		g2.draw(new Line2D.Double(leftX,topY,leftX+width,topY+height));
		//draw a circle with he same center
		double centerX = rect.getCenterX();
		double centerY = rect.getCenterY();
		double radius = 150;
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(centerX, centerY, centerX+radius, centerY+radius);
		g2.draw(circle);
	}
	/**
	 * 
	 */
	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
}
