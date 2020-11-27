/**
 * Title: Subject.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��12������4:30:25
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @class_name:Subject2020��10��12��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��12������4:30:25
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
	 *@Date: 2020��10��12������4:33:26
	 */
	public void add(Observer observer){
		obervers.add(observer);
	}
	/**
	 * @Title: remover
	 *@Description: TODO
	 *@param observer
	 *@author: LiuWei
	 *@Date: 2020��10��12������4:34:00
	 */
	public void remover(Observer observer){
		obervers.remove(observer);
	}
	/**
	 * @Title: notifyObserver
	 *@Description: TODO �����֪ͨ����
	 *@param message
	 *@author: LiuWeinmm
	 *@Date: 2020��10��12������4:34:39
	 */
	public abstract void notifyObserver(String message);
}	
