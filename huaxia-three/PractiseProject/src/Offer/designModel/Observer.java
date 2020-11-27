/**
 * Title: Observer.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午4:31:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Observer2020年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午4:31:44
 */
public interface Observer {
	/**
	 * @Title: dataChange
	 *@Description: TODO 接收到消息
	 *@param message
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午4:32:03
	 */
	void dataChange(String message);
}
