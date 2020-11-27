/**
 * Title: ClientDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午4:20:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.DecoratorPattern;

/**
 * @class_name:ClientDemo2020年1月6日
 * @comments:装饰模式和代理模式，代理模式是接口，装饰模式是类
 * ，这些模式就相当于重写和覆盖，重写方法然后增加一些东邪，抽象成一种模式。其实就是简单的一回事
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午4:20:13
 */
public class ClientDemo {

	/**
	 * 
	 */
	public ClientDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午4:20:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car car = new Benz();
		car.show();
		//对奔驰车进行装饰
		CarDecorator cd = new ConcreteCarDecorator(car);
		cd.show();
	}

}
