/**
 * Title: FixDiscountStrategy.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������10:50:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StrategyPattern;

/**
 * @class_name:FixDiscountStrategy2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������10:50:23
 */
public class FixDiscountStrategy extends DiscountStrategy {

	/**
	 * @param price
	 * @param number
	 */
	public FixDiscountStrategy(double price, int number) {
		super(price, number);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StrategyPattern.DiscountStrategy#calculateDiscount()
	 */
	//ʵ�ֲ��Է������̶��ۿ۶�
	@Override
	public double calculateDiscount() {
		// TODO Auto-generated method stub
		return getNumber()*1;
	}
}
