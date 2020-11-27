/**
 * Title: Collection.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��12������5:02:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Collection2020��10��12��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��12������5:02:24
 */
public interface Collection {
	public Iterator iterator();
	/**
	 * @Title: get
	 *@Description: TODO
	 *@param i ȡ�ü���Ԫ��
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��10��12������5:03:31
	 */
	public Object get(int i);
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param object �򼯺����Ԫ��
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��10��12������5:03:51
	 */
	public boolean add(Object object);
	/**
	 * @Title: size
	 *@Description: TODO
	 *@return ȡ�ü��ϴ�С
	 *@author: LiuWei
	 *@Date: 2020��10��12������5:04:18
	 */
	public int size();
}
