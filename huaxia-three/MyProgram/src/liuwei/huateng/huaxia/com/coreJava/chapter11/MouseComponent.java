/**
 * Title: MouseComponent.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月14日上午9:53:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter11;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @class_name:MouseComponent2020年1月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月14日上午9:53:35
 */
public class MouseComponent extends JComponent {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private static final int SIDELENGTH = 10;
	private ArrayList<Rectangle2D> squares;
	//the square containing the mouse cursor
	private Rectangle2D current;
	/**
	 * 
	 */
	public MouseComponent() {
		// TODO Auto-generated constructor stub
		squares = new ArrayList<>();
		current = null;
		
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月14日上午9:53:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				JFrame frame = new JFrame();
				frame.add(new MouseComponent());
				frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
				frame.setTitle("mouseTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
	}
	/**
	 * 覆盖重新绘图的方法
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		//draw all squares
		for(Rectangle2D r:squares)
			g2.draw(r);
	}
	/***
	 * @Title: find
	 *@Description: TODO
	 *@param p
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月14日上午10:13:14
	 */
	public Rectangle2D find(Point2D p)
	{
		for(Rectangle2D r :squares)
		{
			if(r.contains(p)) 
			return r;
		}
		return null;
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param p
	 *@author: LiuWei
	 *@Date: 2020年1月14日上午10:56:42
	 */
	public void add(Point2D p){
		double x = p.getX();
		double y = p.getY();
		current = new Rectangle2D.Double(x- SIDELENGTH/2,y-SIDELENGTH/2,SIDELENGTH,SIDELENGTH);
		squares.add(current);
		repaint();
	}
	/**
	 * @Title: remove
	 *@Description: TODO
	 *@param s
	 *@author: LiuWei
	 *@Date: 2020年1月14日上午10:57:51
	 */
	public void remove(Rectangle2D s){
		if(s == null )
			return;
		if(s == current) 
			current = null;
		squares.remove(s);
		repaint();
	}
	/**
	 * 
	 */
	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	/**
	 * 
	 * @class_name:MouseHandler2020年1月14日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年1月14日上午9:59:13
	 */
	private class MouseHandler extends MouseAdapter
	{
		/**
		 * 
		 */
		public void mousePressed(MouseEvent event)
		{
			current = find(event.getPoint());
			if(current == null) 
				add(event.getPoint());
		}
		public void mouseClicked(MouseEvent event)
		{
			current= find(event.getPoint());
			if(current == null && event.getClickCount() >= 2)
				remove(current);
		}
	}
	/**
	 * 
	 * @class_name:MouseMotionHandler2020年1月14日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年1月14日上午10:03:40
	 */
	private class MouseMotionHandler implements MouseMotionListener
	{

		@Override
		public void mouseDragged(MouseEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent event) {
			// TODO Auto-generated method stub
			if(current != null)
			{
				int x = event.getX();
				int y = event.getY();
				current.setFrame(x-SIDELENGTH/2,y-SIDELENGTH/2,SIDELENGTH,SIDELENGTH);
				repaint();
			}
		}
		
	}
}
