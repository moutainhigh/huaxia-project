/**
 * Title: ContextClient.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日上午10:52:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StrategyPattern;

/**
 * @class_name:ContextClient2020年1月7日
 * @comments:策略模式
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日上午10:52:16
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
	 *@Description: TODO 调用策略模式，计算折扣额
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月7日上午10:53:28
	 */
	public double contextCaldisc(){
		return ds.calculateDiscount();
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月7日上午10:52:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContextClient context0 = new ContextClient(new NoDiscountStrategy(48.5,20));
		System.out.println("0折扣："+context0.contextCaldisc());
		ContextClient contextFix = new ContextClient(new FixDiscountStrategy(46,60));
		System.out.println("固定折扣："+contextFix.contextCaldisc());
		ContextClient contextPer = new ContextClient(new PercentageDiscountStrategy(38,40));
		System.out.println("15%的折扣"+contextPer.contextCaldisc());
	}

}
