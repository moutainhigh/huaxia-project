/**
 * Title: ConcreteObserver.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午4:42:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

import java.util.Observable;

/**
 * @class_name:ConcreteObserver2020年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午4:42:17
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
