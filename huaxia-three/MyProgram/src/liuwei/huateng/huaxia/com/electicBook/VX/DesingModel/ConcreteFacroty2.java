/**
 * Title: ConcreteFacroty1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日上午10:10:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:ConcreteFacroty12020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日上午10:10:48
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
