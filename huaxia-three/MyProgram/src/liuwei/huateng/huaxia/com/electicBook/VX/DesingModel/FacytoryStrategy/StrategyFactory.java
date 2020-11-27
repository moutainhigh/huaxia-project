/**
 * Title: StrategyFactory.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������3:29:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:StrategyFactory2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������3:29:32
 */
public class StrategyFactory {

	/**
	 * 
	 */
	public StrategyFactory() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: getDeduction
	 *@Description: TODO ���Թ�������������
	 *@param strategy
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��8������3:31:25
	 */
	public static IDeduction getDeduction(StrategyMan strategy){
		IDeduction deduction = null;
		try{
			deduction = (IDeduction)Class.forName(strategy.getValue()).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return deduction;
	}
}
