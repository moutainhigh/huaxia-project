/**
 * Title: BB.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������4:13:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:BB2020��9��22��
 * @comments:Runnableʵ���߳�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������4:13:38
 */
public class BB2 implements Runnable {
	int time;
	private Thread th;
	/**
	 * Constructor
	 */
	public BB2(String name,int n) {
		// TODO Auto-generated constructor stub
		time = n;
		th = new Thread(this,name);
		th.start();
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��22������4:13:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BB2 b1 = new BB2("�߳�1",500);
		BB2 b2 = new BB2("�߳�2",200);
		BB2 b3 = new BB2("�߳�3",300);
	}
	/**
	 * Thread��run����
	 */
	public void run(){
		for(int i=1;i<=5;i++){
			System.out.println(Thread.currentThread().getName()+" "+i+"��");
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i == 5){
				System.out.println(Thread.currentThread().getName()+"�˳���");
			}
		}
	}
}
