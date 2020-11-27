/**
 * Title: Iterator.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午5:05:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Iterator2020年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午5:05:01
 */
public interface Iterator {
	/**
	 * @Title: previous
	 *@Description: TODO 指针前移
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午5:05:40
	 */
	public Object previous();
	/**
	 * @Title: next
	 *@Description: TODO
	 *@return 指针后移
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午5:06:03
	 */
	public Object next();
	public boolean hasNext();
}
