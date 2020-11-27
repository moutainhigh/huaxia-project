/**
 * Title: StrategyFactory.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午3:29:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:StrategyFactory2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午3:29:32
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
	 *@Description: TODO 策略工厂，生产对象
	 *@param strategy
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午3:31:25
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
