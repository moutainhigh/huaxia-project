/**
 * Title: Circle.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������4:50:11
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BridgePattern;

/**
 * @class_name:Circle2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������4:50:11
 */
public class Circle extends AbstractShape {
	
	/**
	 * @param color
	 */
	public Circle(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BridgePattern.AbstractShape#draw()
	 */
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("ʹ��"+color.getColor()+"��Բ��");
	}

}
