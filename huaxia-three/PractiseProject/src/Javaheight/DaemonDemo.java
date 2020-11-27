/**
 * Title: DaemonDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��13������10:52:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

/**
 * @class_name:DaemonDemo2020��10��13��
 * @comments: �ػ��̣߳������е��߳�ִ����֮�����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��13������10:52:32
 */
public class DaemonDemo {

	/**
	 * Constructor
	 */
	public DaemonDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��10��13������10:52:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = new DaemonT();
		t.setDaemon(true);
		t.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @class_name:DaemonT2020��10��13��
	 * @comments: DaemonT ׼������Ϊ�ػ��߳�
	 * @param:
	 * @return:
	 * @author ��ΰ/liuwei
	 * @createtime:2020��10��13������10:53:39
	 */
	public static class DaemonT extends Thread{
		public void run(){
			while(true){
				System.out.println("I am alive��");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
