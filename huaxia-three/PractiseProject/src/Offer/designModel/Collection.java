/**
 * Title: Collection.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午5:02:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Collection2020年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午5:02:24
 */
public interface Collection {
	public Iterator iterator();
	/**
	 * @Title: get
	 *@Description: TODO
	 *@param i 取得集合元素
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午5:03:31
	 */
	public Object get(int i);
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param object 向集合添加元素
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午5:03:51
	 */
	public boolean add(Object object);
	/**
	 * @Title: size
	 *@Description: TODO
	 *@return 取得集合大小
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午5:04:18
	 */
	public int size();
}
