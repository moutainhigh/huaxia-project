/**
 * Title: Trade.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������3:08:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:Trade2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������3:08:06
 */
public class Trade {
	//���ױ��
	private String tradeNo = "";
	//���׽��
	private double amount = 0;
	/**
	 * 
	 */
	public Trade() {
		// TODO Auto-generated constructor stub
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Trade [tradeNo=" + tradeNo + ", amount=" + amount + "]";
	}
	
}
