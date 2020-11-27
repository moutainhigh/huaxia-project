/**
 * Title: Ball.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月17日下午2:42:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter14;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @class_name:Ball2020年1月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月17日下午2:42:24
 */
public class Ball {
	private static final int XSIZE = 15;
	private static final int YSIZE = 15;
	private double x= 0;
	private double y = 0;
	private double dx = 1;
	private double dy = 1;
	/**
	 * 
	 */
	public Ball() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午2:42:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * @Title: move
	 *@Description: TODO
	 *@param bounds  移动方法
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午2:42:59
	 */
	public void move(Rectangle2D bounds)
	{
		x+=dx;
		y+=dy;
		if(x<bounds.getMinX())
		{
			x= bounds.getMinX();
			dx= -dx;
		}
		if(x+XSIZE>=bounds.getMaxX())
		{
			x = bounds.getMaxX() -XSIZE;
			dx = -dx;
		}
		if(y<bounds.getMinY())
		{
			y = bounds.getMinY();
			dy = -dy;
		}
		if(y+YSIZE >= bounds.getMaxY())
		{
			y = bounds.getMaxY() -YSIZE;
			dy = -dy;
		}
	}
	/**
	 * @Title: getShape
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午2:45:59
	 */
	public Ellipse2D getShape()
	{
		return new Ellipse2D.Double(x,y,XSIZE,YSIZE);
	}
}
