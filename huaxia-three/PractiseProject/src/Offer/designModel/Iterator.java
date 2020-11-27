/**
 * Title: Iterator.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��12������5:05:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Iterator2020��10��12��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��12������5:05:01
 */
public interface Iterator {
	/**
	 * @Title: previous
	 *@Description: TODO ָ��ǰ��
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��10��12������5:05:40
	 */
	public Object previous();
	/**
	 * @Title: next
	 *@Description: TODO
	 *@return ָ�����
	 *@author: LiuWei
	 *@Date: 2020��10��12������5:06:03
	 */
	public Object next();
	public boolean hasNext();
}
