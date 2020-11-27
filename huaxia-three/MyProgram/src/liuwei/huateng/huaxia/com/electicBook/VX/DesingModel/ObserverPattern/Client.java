/**
 * Title: Client.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午10:43:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern;

/**
 * @class_name:Client2020年1月8日
 * @comments:观察者模式的测试
 * 定义对象之间一对多的关系，当一个对象的状态改变时候，所有依赖于它的对象都会被通知并且更新
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午10:43:53
 */
public class Client {

	/**
	 * 
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午10:43:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建一个主题对象
		ConcreteSubject subject = new ConcreteSubject();
		//创建一个观察者
		Observer obs = new ConcreteObserver();
		Observer obs2 = new ConcreteObserver();

		//登记观察者
		subject.attach(obs);
		subject.attach(obs2);
		//改变状态
		subject.chage();
	}

}
