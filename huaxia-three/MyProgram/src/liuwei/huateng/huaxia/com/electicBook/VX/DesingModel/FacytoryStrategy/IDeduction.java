/**
 * Title: IDeduction.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������3:09:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:IDeduction2020��1��8��
 * @comments: 
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������3:09:54
 */
public interface IDeduction {
	/**
	 * @Title: exec
	 *@Description: TODO �ۿ����
	 *@param card
	 *@param trade
	 *@author: LiuWei
	 *@Date: 2020��1��8������3:10:26
	 */
	public boolean exec(Card card,Trade trade);
}
