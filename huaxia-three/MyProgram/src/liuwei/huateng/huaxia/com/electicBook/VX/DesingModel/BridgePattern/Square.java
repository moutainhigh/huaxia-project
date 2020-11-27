/**
 * Title: Square.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午4:51:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BridgePattern;

/**
 * @class_name:Square2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午4:51:09
 */
public class Square extends AbstractShape {

	/**
	 * @param color
	 */
	public Square(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BridgePattern.AbstractShape#draw()
	 */
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("使用"+color.getColor()+"画正方形");
	}

}
