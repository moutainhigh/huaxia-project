/**
 * Title: Aggregate.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日下午1:46:57
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.IneratorPattern;

/**
 * @class_name:Aggregate2020年1月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日下午1:46:57
 */
public interface Aggregate {
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param obj
	 *@author: LiuWei
	 *@Date: 2020年1月7日下午1:48:31
	 */
	public void add(Object obj);
	/**
	 * @Title: createIterator
	 *@Description: TODO 
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月7日下午1:48:37
	 */
	public Iterator createIterator();
}
