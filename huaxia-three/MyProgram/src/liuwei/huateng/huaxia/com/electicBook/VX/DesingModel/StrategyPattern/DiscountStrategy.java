/**
 * Title: DiscountStrategy.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日上午10:47:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StrategyPattern;

/**
 * @class_name:DiscountStrategy2020年1月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日上午10:47:05
 */
public abstract class DiscountStrategy {
	//书的价格
	private double price = 0;
	//书的数量
	private int number = 0;
	/**
	 * 
	 */
	public DiscountStrategy(double price,int number) {
		// TODO Auto-generated constructor stub
		this.price = price;
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public int getNumber() {
		return number;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "DiscountStrategy [price=" + price + ", number=" + number + "]";
	}
	//策略方法，计算折扣额度
	public abstract double calculateDiscount();
}
