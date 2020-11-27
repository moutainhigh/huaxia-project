/**
 * Title: Subjectd.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������10:25:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern;

/**
 * @class_name:Subjectd2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������10:25:01
 */
public interface Subjectd {
	/**
	 * @Title: attach
	 *@Description: TODO �Ǽ�һ���µĹ۲���
	 *@param obs
	 *@author: LiuWei
	 *@Date: 2020��1��8������10:25:28
	 */
	public void attach(Observer obs);
	/**
	 * @Title: detach
	 *@Description: TODOɾ��һ���Ǽǹ��Ĺ۲���
	 *@param obs
	 *@author: LiuWei
	 *@Date: 2020��1��8������10:36:39
	 */
	public void detach(Observer obs);
	/**
	 * @Title: notifyObserver
	 *@Description: TODO ֪ͨ���еǼǹ��Ĺ۲��߶���
	 *@author: LiuWei
	 *@Date: 2020��1��8������10:36:42
	 */
	public void notifyObserver();
}
