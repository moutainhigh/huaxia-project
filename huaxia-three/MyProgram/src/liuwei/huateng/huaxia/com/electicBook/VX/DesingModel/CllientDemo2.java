/**
 * Title: CllientDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��3������4:52:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:CllientDemo2020��1��3��
 * @comments:����ģʽ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��3������4:52:19
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
	 *@Date: 2020��1��3������4:52:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ƻ��԰������
		AbstractFactory factory1 = new  ConcreteFacroty1();
		AbstractFactory factory2 = new  ConcreteFacroty2();
		//�����ȼ�Ϊ1�Ĳ�ƷA
		ProductA a1 = factory1.factoryA();
		//�����ȼ�Ϊ2�Ĳ�ƷA
		ProductA a2 = factory2.factoryA();
		//�����ȼ�Ϊ1�Ĳ�ƷB
		ProductB b1 = factory1.factoryB();
		//�����ȼ�Ϊ2�Ĳ�ƷB
		ProductB b2 = factory2.factoryB();
		//ҵ����
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
