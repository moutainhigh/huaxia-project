/**
 * Title: FixDiscountStrategy.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日上午10:50:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StrategyPattern;

/**
 * @class_name:FixDiscountStrategy2020年1月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日上午10:50:23
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
	//实现策略方法，固定折扣额
	@Override
	public double calculateDiscount() {
		// TODO Auto-generated method stub
		return getNumber()*1;
	}
}
