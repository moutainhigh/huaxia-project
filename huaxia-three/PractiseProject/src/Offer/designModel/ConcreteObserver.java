/**
 * Title: ConcreteObserver.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��12������4:42:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

import java.util.Observable;

/**
 * @class_name:ConcreteObserver2020��10��12��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��12������4:42:17
 */
public class ConcreteObserver implements Observer {
	
	/**
	 * Constructor
	 */
	public ConcreteObserver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dataChange(String message) {
		// TODO Auto-generated method stub
		System.out.println("recive message:"+message);
	}

}
