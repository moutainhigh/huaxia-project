/**
 * Title: DeductionFacade.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午3:32:45
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:DeductionFacade2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午3:32:45
 */
public class DeductionFacade {

	/**
	 * 
	 */
	public DeductionFacade() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: getDeductionType
	 *@Description: TODO获取对应的消费策略
	 *@param trade
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午3:33:44
	 */
	private static StrategyMan getDeductionType(Trade trade){
		if(trade.getTradeNo().substring(0,3).equals("000")){
			return StrategyMan.SteadyDeduction;
		}else{
			return StrategyMan.FreeDeduction;
		}
	}
	/**
	 * @Title: deduct
	 *@Description: TODO 对外公布的扣款操作
	 *@param card
	 *@param trade
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午3:35:28
	 */
	public static void deduct(Card card ,Trade trade){
		//获得的消费策略
		 StrategyMan sm= getDeductionType(trade);
		 //根据消费策略获取消费策略对象
		 IDeduction deduction = StrategyFactory.getDeduction(sm);
		 //进行扣款处理
		 if(deduction.exec(card, trade)){
			 System.out.println("============交易凭证====================");
			 System.out.println(trade.getTradeNo()+"交易成功！ ");
			 System.out.println("交易金额是："+trade.getAmount());
			 System.out.println("===============================");
		 }else{
			 System.out.println("==========================交易失败====================");
		 }
	}
}
