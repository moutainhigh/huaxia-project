/**
 * Title: Test.java
 * Description:��������ģʽ
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������4:53:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BridgePattern;

/**
 * @class_name:Test2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������4:53:24
 */
public class Test {

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��6������4:53:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Color color = new Green(1);
		AbstractShape shape = new Square(color);
		shape.draw();
		Color color2 = new Red(1);
		AbstractShape shape2 = new Circle(color2);
		shape2.draw();
	}

}
