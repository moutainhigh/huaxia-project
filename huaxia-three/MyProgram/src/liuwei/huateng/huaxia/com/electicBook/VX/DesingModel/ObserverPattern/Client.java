/**
 * Title: Client.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������10:43:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern;

/**
 * @class_name:Client2020��1��8��
 * @comments:�۲���ģʽ�Ĳ���
 * �������֮��һ�Զ�Ĺ�ϵ����һ�������״̬�ı�ʱ���������������Ķ��󶼻ᱻ֪ͨ���Ҹ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������10:43:53
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
	 *@Date: 2020��1��8������10:43:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����һ���������
		ConcreteSubject subject = new ConcreteSubject();
		//����һ���۲���
		Observer obs = new ConcreteObserver();
		Observer obs2 = new ConcreteObserver();

		//�Ǽǹ۲���
		subject.attach(obs);
		subject.attach(obs2);
		//�ı�״̬
		subject.chage();
	}

}
