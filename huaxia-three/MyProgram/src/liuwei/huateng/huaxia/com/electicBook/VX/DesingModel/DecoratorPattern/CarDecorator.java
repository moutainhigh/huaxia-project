/**
 * Title: CarDecorator.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午4:16:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.DecoratorPattern;

/**
 * @class_name:CarDecorator2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午4:16:00
 */
public abstract class CarDecorator implements Car {
	private Car car = null;
	/**
	 * 
	 */
	public CarDecorator(Car car) {
		// TODO Auto-generated constructor stub
		this.car = car;
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.DecoratorPattern.Car#show()
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub
		this.car.show();
	}

}
