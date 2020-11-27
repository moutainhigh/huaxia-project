/**
 * Title: Aggregate.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������1:46:57
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.IneratorPattern;

/**
 * @class_name:Aggregate2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������1:46:57
 */
public interface Aggregate {
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param obj
	 *@author: LiuWei
	 *@Date: 2020��1��7������1:48:31
	 */
	public void add(Object obj);
	/**
	 * @Title: createIterator
	 *@Description: TODO 
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��7������1:48:37
	 */
	public Iterator createIterator();
}
