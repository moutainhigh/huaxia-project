/**
 * Title: Card.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������3:03:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:Card2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������3:03:38
 */
public class Card {
	//IC����
	private String cardNo;
	//���ڹ̶����
	private double steadyMoney = 0;
	//���ɽ��׽��
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
	 *@Description: TODO���IC�е���Ϣ
	 *@author: LiuWei
	 *@Date: 2020��1��8������3:07:04
	 */
	public void showCard(){
		System.out.println("IC���ţ�"+this.cardNo);
		System.out.println("�̶���"+this.steadyMoney);
		System.out.println("���ɽ�"+this.freeMoney);

	}
}
