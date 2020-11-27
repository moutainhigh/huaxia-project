/**
 * Title: DiscountStrategy.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������10:47:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StrategyPattern;

/**
 * @class_name:DiscountStrategy2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������10:47:05
 */
public abstract class DiscountStrategy {
	//��ļ۸�
	private double price = 0;
	//�������
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
	//���Է����������ۿ۶��
	public abstract double calculateDiscount();
}
