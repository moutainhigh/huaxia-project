/**
 * Title: ContextClient.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������10:52:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StrategyPattern;

/**
 * @class_name:ContextClient2020��1��7��
 * @comments:����ģʽ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������10:52:16
 */
public class ContextClient {
	private DiscountStrategy ds;
	/**
	 * 
	 */
	public ContextClient(DiscountStrategy ds) {
		// TODO Auto-generated constructor stub
		this.ds = ds;
	}
	/**
	 * @Title: contextCaldisc
	 *@Description: TODO ���ò���ģʽ�������ۿ۶�
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��7������10:53:28
	 */
	public double contextCaldisc(){
		return ds.calculateDiscount();
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��7������10:52:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContextClient context0 = new ContextClient(new NoDiscountStrategy(48.5,20));
		System.out.println("0�ۿۣ�"+context0.contextCaldisc());
		ContextClient contextFix = new ContextClient(new FixDiscountStrategy(46,60));
		System.out.println("�̶��ۿۣ�"+contextFix.contextCaldisc());
		ContextClient contextPer = new ContextClient(new PercentageDiscountStrategy(38,40));
		System.out.println("15%���ۿ�"+contextPer.contextCaldisc());
	}

}
