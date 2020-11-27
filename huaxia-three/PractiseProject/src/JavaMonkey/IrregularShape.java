/**
 * Title: IrregularShape.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日上午9:43:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;

/**
 * @class_name:IrregularShape2020年9月18日
 * @comments: 自我绘图工具的实现
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日上午9:43:32
 */
public class IrregularShape extends JFrame{
	GeneralPath  gPath = new GeneralPath();
	Point aPoint;
	/**
	 * Constructor
	 */
	public IrregularShape() {
		// TODO Auto-generated constructor stub
		super("不规则图形的绘制");
		enableEvents(AWTEvent.MOUSE_EVENT_MASK|AWTEvent.MOUSE_MOTION_EVENT_MASK);
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * 覆盖绘图方法
	 */
	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		g2D.draw(gPath);
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日上午9:43:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new IrregularShape();
	}
	/**
	 * 
	 */
	protected void processMouseEvent(MouseEvent e){
		if(e.getID() == MouseEvent.MOUSE_PRESSED){
			aPoint = e.getPoint();
			gPath =   new GeneralPath();
			gPath.moveTo(aPoint.x, aPoint.y);
			
		}
	}
	protected void processMouseMotionEvent(MouseEvent e){
		if(e.getID() == MouseEvent.MOUSE_DRAGGED){
			aPoint = e.getPoint();
			gPath.lineTo(aPoint.x, aPoint.y);
			gPath.moveTo(aPoint.x, aPoint.y);
			repaint();
		}
	}
}
