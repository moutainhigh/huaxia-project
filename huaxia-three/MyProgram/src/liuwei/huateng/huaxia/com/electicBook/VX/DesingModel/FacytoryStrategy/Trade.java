/**
 * Title: Trade.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午3:08:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:Trade2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午3:08:06
 */
public class Trade {
	//交易编号
	private String tradeNo = "";
	//交易金额
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
