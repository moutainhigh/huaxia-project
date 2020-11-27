/**
 * Title: DeductionFacade.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������3:32:45
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:DeductionFacade2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������3:32:45
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
	 *@Description: TODO��ȡ��Ӧ�����Ѳ���
	 *@param trade
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��8������3:33:44
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
	 *@Description: TODO ���⹫���Ŀۿ����
	 *@param card
	 *@param trade
	 *@author: LiuWei
	 *@Date: 2020��1��8������3:35:28
	 */
	public static void deduct(Card card ,Trade trade){
		//��õ����Ѳ���
		 StrategyMan sm= getDeductionType(trade);
		 //�������Ѳ��Ի�ȡ���Ѳ��Զ���
		 IDeduction deduction = StrategyFactory.getDeduction(sm);
		 //���пۿ��
		 if(deduction.exec(card, trade)){
			 System.out.println("============����ƾ֤====================");
			 System.out.println(trade.getTradeNo()+"���׳ɹ��� ");
			 System.out.println("���׽���ǣ�"+trade.getAmount());
			 System.out.println("===============================");
		 }else{
			 System.out.println("==========================����ʧ��====================");
		 }
	}
}
