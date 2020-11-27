/**
 * Title: TestObserver.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午4:43:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestObserver2020年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午4:43:54
 */
public class TestObserver {

	/**
	 * Constructor
	 */
	public TestObserver() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午4:43:54 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subject subject = new ConcreteSubject();
		Observer obs = new ConcreteObserver();
		subject.add(obs);
		subject.notifyObserver("data1");
	}

}
