/**
 * Title: Multithread.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��21������4:48:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:Multithread2020��9��21��
 * @comments:�̵߳���������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��21������4:48:47
 */
public class Multithread {

	/**
	 * Constructor
	 */
	public Multithread() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��21������4:48:47
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ThreadState state = new ThreadState();
		Thread thread = new Thread(state);
		System.out.println("�½��̣߳�"+thread.getState());
		thread.start();
		System.out.println("�����̣߳�"+thread.getState());
		Thread.sleep(100);
		System.out.println("��ʱ�ȴ���"+thread.getState());
		Thread.sleep(1000);
		System.out.println("�ȴ��̣߳�"+thread.getState());
		state.notifyNow();
		System.out.println("�����̣߳�"+thread.getState());
		Thread.sleep(1000);
		System.out.println("��ֹ�̣߳�"+thread.getState());
	}
}
