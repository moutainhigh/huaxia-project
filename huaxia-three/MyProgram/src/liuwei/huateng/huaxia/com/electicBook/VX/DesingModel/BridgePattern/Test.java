/**
 * Title: Test.java
 * Description:测试桥梁模式
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午4:53:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BridgePattern;

/**
 * @class_name:Test2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午4:53:24
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
	 *@Date: 2020年1月6日下午4:53:24
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
