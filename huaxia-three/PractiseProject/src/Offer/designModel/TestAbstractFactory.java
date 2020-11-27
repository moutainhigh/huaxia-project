/**
 * Title: TestAbstractFactory.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��28������4:59:25
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestAbstractFactory2020��9��28��
 * @comments:���󹤳���ʹ�ã������ĳ������ֻ����̣����Թ������������������ֻ����ԣ�һ��һЩ�����Ķ�����
 * @param:��������
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��28������4:59:25
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
	 *@Date: 2020��9��28������4:59:25
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
