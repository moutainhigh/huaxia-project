/**
 * Title: Subjectd.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午10:25:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern;

/**
 * @class_name:Subjectd2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午10:25:01
 */
public interface Subjectd {
	/**
	 * @Title: attach
	 *@Description: TODO 登记一个新的观察者
	 *@param obs
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午10:25:28
	 */
	public void attach(Observer obs);
	/**
	 * @Title: detach
	 *@Description: TODO删除一个登记过的观察者
	 *@param obs
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午10:36:39
	 */
	public void detach(Observer obs);
	/**
	 * @Title: notifyObserver
	 *@Description: TODO 通知所有登记过的观察者对象
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午10:36:42
	 */
	public void notifyObserver();
}
