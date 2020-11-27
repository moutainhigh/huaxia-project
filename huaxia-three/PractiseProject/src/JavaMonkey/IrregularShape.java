/**
 * Title: IrregularShape.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������9:43:32
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
 * @class_name:IrregularShape2020��9��18��
 * @comments: ���һ�ͼ���ߵ�ʵ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������9:43:32
 */
public class IrregularShape extends JFrame{
	GeneralPath  gPath = new GeneralPath();
	Point aPoint;
	/**
	 * Constructor
	 */
	public IrregularShape() {
		// TODO Auto-generated constructor stub
		super("������ͼ�εĻ���");
		enableEvents(AWTEvent.MOUSE_EVENT_MASK|AWTEvent.MOUSE_MOTION_EVENT_MASK);
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * ���ǻ�ͼ����
	 */
	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		g2D.draw(gPath);
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��18������9:43:32
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
