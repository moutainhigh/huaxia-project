/**
 * Title: ConcreteSubject.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午10:38:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @class_name:ConcreteSubject2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午10:38:06
 */
public class ConcreteSubject implements Subjectd {
	private List<Observer> obsVector = new ArrayList<Observer>();
	/**
	 * 
	 */
	public ConcreteSubject() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc) 登记一个新的观察者
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern.Subjectd#attach(liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern.Observer)
	 */
	@Override
	public void attach(Observer obs) {
		// TODO Auto-generated method stub
		obsVector.add(obs);
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern.Subjectd#detach(liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern.Observer)
	 */
	@Override
	public void detach(Observer obs) {
		// TODO Auto-generated method stub
		obsVector.remove(obs);

	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern.Subjectd#notifyObserver()
	 */
	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for(Observer e :obsVector){
			e.upodate();
		}
	}	
	/**
	 * @Title: chage
	 *@Description: TODO 业务方法，改变状态
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午10:42:18
	 */
	public void chage(){
		this.notifyObserver();
	}
}
