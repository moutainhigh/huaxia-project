/**
 * Title: Card.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午3:03:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:Card2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午3:03:38
 */
public class Card {
	//IC卡号
	private String cardNo;
	//卡内固定金额
	private double steadyMoney = 0;
	//自由交易金额
	private double freeMoney = 0;
	/**
	 * 
	 */
	public Card(String cardNo,double steadyMoney,double freeMoney) {
		// TODO Auto-generated constructor stub
		this.cardNo = cardNo;
		this.steadyMoney = steadyMoney;
		this.freeMoney = freeMoney;
	}
	public String getCardNo() {
		return cardNo;
	}
	public double getSteadyMoney() {
		return steadyMoney;
	}
	public double getFreeMoney() {
		return freeMoney;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public void setSteadyMoney(double steadyMoney) {
		this.steadyMoney = steadyMoney;
	}
	public void setFreeMoney(double freeMoney) {
		this.freeMoney = freeMoney;
	}
	@Override
	public String toString() {
		return "Card [cardNo=" + cardNo + ", steadyMoney=" + steadyMoney + ", freeMoney=" + freeMoney + "]";
	}
	/**
	 * @Title: showCard
	 *@Description: TODO输出IC中的信息
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午3:07:04
	 */
	public void showCard(){
		System.out.println("IC卡号："+this.cardNo);
		System.out.println("固定金额："+this.steadyMoney);
		System.out.println("自由金额："+this.freeMoney);

	}
}
