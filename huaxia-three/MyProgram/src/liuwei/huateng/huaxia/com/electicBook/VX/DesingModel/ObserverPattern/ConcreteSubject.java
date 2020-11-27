/**
 * Title: ConcreteSubject.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������10:38:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @class_name:ConcreteSubject2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������10:38:06
 */
public class ConcreteSubject implements Subjectd {
	private List<Observer> obsVector = new ArrayList<Observer>();
	/**
	 * 
	 */
	public ConcreteSubject() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc) �Ǽ�һ���µĹ۲���
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
	 *@Description: TODO ҵ�񷽷����ı�״̬
	 *@author: LiuWei
	 *@Date: 2020��1��8������10:42:18
	 */
	public void chage(){
		this.notifyObserver();
	}
}
