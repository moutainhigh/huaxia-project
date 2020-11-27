/**
 * Title: CllientDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月3日下午4:52:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:CllientDemo2020年1月3日
 * @comments:工厂模式生产
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月3日下午4:52:19
 */
public class CllientDemo2 {

	/**
	 * 
	 */
	public CllientDemo2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月3日下午4:52:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//苹果园丁工厂
		AbstractFactory factory1 = new  ConcreteFacroty1();
		AbstractFactory factory2 = new  ConcreteFacroty2();
		//生产等级为1的产品A
		ProductA a1 = factory1.factoryA();
		//生产等级为2的产品A
		ProductA a2 = factory2.factoryA();
		//生产等级为1的产品B
		ProductB b1 = factory1.factoryB();
		//生产等级为2的产品B
		ProductB b2 = factory2.factoryB();
		//业务处理
		a1.method1();
		a1.method2();
		a2.method1();
		a2.method2();
		b1.method1();
		b1.method2();
		b2.method1();
		b2.method2();
	}	

}
