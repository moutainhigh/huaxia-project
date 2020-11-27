/**
 * Title: TestAbstractFactory.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月28日下午4:59:25
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestAbstractFactory2020年9月28日
 * @comments:抽象工厂的使用，工厂的抽象，有手机工程，电脑工厂。工厂可以生产手机电脑，一起一些其他的东西。
 * @param:二级抽象
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月28日下午4:59:25
 */
public class TestAbstractFactory {

	/**
	 * Constructor
	 */
	public TestAbstractFactory() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午4:59:25
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractFactory phoneFactory = new PhoneFactory2();
		Phone2 phoneHuawei = phoneFactory.createPhone("HuaWei");
		Phone2 phoneApple = phoneFactory.createPhone("Apple");
		System.out.println(phoneHuawei.call());
		System.out.println(phoneApple.call());
		AbstractFactory computerFactory = new ComputerFactory();
		Computer computerHuaWei = computerFactory.createComputer("HuaWei");
		Computer computerAppter = computerFactory.createComputer("Apple");
		System.out.println(computerHuaWei.internet());
		System.out.println(computerHuaWei.internet());
	}
}
