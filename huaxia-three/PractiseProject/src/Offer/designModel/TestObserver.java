/**
 * Title: TestObserver.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��12������4:43:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestObserver2020��10��12��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��12������4:43:54
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
	 *@Date: 2020��10��12������4:43:54 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subject subject = new ConcreteSubject();
		Observer obs = new ConcreteObserver();
		subject.add(obs);
		subject.notifyObserver("data1");
	}

}
