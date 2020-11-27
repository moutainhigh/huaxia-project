/**
 * Title: Ball.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��21������9:49:57
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @class_name:Ball2020��8��21��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��21������9:49:57
 */
public class Ball {
	private static final int XSIZE = 15;
	private static final int YSIZE = 15;
	private double  x= 0 ;
	private double  y = 0;
	private double dx = 1;
	private double dy = 1;
	/**
	 * Constructor
	 */
	public Ball() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��21������9:49:57
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * @Title: move
	 *@Description: TODO
	 *@param bounds
	 *@author: LiuWei
	 *@Date: 2020��8��21������9:52:21
	 */
	public void move(Rectangle2D bounds){
		x += dx;
		y += dy;
		if(x < bounds.getMinX()){
			x = bounds.getMinX();
		dx = -dx;
		}
		if(x + XSIZE >= bounds.getMaxX()){
			x = bounds.getMaxX() - XSIZE;
			dx = -dx;
		}
		if(y < bounds.getMinY()){
			y = bounds.getMinY();
			dy = -dy;
		}
		if(y+ YSIZE >= bounds.getMaxY()){
			y = bounds.getMaxY() - YSIZE;
			dy = -dy;
		}
	}
	/**
	 * @Title: getShape
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��21������10:02:34
	 */
	public Ellipse2D getShape()
	{
		return new Ellipse2D.Double(x,y,XSIZE,YSIZE);
	}
}
