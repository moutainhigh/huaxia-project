/**
 * Title: PercentageDiscountStrategy.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������10:51:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StrategyPattern;

/**
 * @class_name:PercentageDiscountStrategy2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������10:51:37
 */
public class PercentageDiscountStrategy extends DiscountStrategy {

	/**
	 * @param price
	 * @param number
	 */
	public PercentageDiscountStrategy(double price, int number) {
		super(price, number);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StrategyPattern.DiscountStrategy#calculateDiscount()
	 */
	@Override
	public double calculateDiscount() {
		// TODO Auto-generated method stub
		return getNumber()*getPrice()*0.15;
	}

}
