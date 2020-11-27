/**
 * Title: IDeduction.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午3:09:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:IDeduction2020年1月8日
 * @comments: 
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午3:09:54
 */
public interface IDeduction {
	/**
	 * @Title: exec
	 *@Description: TODO 扣款操作
	 *@param card
	 *@param trade
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午3:10:26
	 */
	public boolean exec(Card card,Trade trade);
}
