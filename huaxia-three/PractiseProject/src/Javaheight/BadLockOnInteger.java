/**
 * Title: BadLockOnInteger.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��13������3:11:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

/**
 * @class_name:BadLockOnInteger2020��10��13��
 * @comments: ���̵߳����⣬�̲߳���ȫ�ġ����̻߳�������⡣INteger�������ö���Ҳ��������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��13������3:11:06
 */
public class BadLockOnInteger implements Runnable {
	public static Integer i = 0;
	static BadLockOnInteger instance = new BadLockOnInteger();
	/**
	 * Constructor
	 */
	public BadLockOnInteger() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int j =0;j<10000000;j++){
			synchronized(i){
				i++;
			}
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��10��13������3:11:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
	}

}
