/**
 * Title: CarDecorator.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������4:16:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.DecoratorPattern;

/**
 * @class_name:CarDecorator2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������4:16:00
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
