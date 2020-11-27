/**
 * Title: Subject.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午4:30:25
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @class_name:Subject2020年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午4:30:25
 */
public abstract class Subject {
	protected List<Observer> obervers = new ArrayList<Observer>();
	/**
	 * Constructor
	 */
	public Subject() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param observer
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午4:33:26
	 */
	public void add(Observer observer){
		obervers.add(observer);
	}
	/**
	 * @Title: remover
	 *@Description: TODO
	 *@param observer
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午4:34:00
	 */
	public void remover(Observer observer){
		obervers.remove(observer);
	}
	/**
	 * @Title: notifyObserver
	 *@Description: TODO 抽象的通知方法
	 *@param message
	 *@author: LiuWeinmm
	 *@Date: 2020年10月12日下午4:34:39
	 */
	public abstract void notifyObserver(String message);
}	
