/**
 * Title: ConcreteFacroty1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������10:10:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:ConcreteFacroty12020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������10:10:48
 */
public class ConcreteFacroty2 implements AbstractFactory {

	/**
	 * 
	 */
	public ConcreteFacroty2() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.AbstractFactory#factoryA()
	 */
	@Override
	public ProductA factoryA() {
		// TODO Auto-generated method stub
		return new ProductA2();
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.AbstractFactory#factoryB()
	 */
	@Override
	public ProductB factoryB() {
		// TODO Auto-generated method stub
		return new ProductB2();
	}

}
