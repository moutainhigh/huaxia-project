/**
 * Title: Benz.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午4:03:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.DecoratorPattern;

/**
 * @class_name:Benz2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午4:03:05
 */
public class Benz implements Car {

	/**
	 * 
	 */
	public Benz() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.DecoratorPattern.Car#show()
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub
		System.out.println("奔驰车的颜色是黑色");
	}

}
