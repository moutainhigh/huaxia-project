/**
 * Title: ThreadDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������3:57:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:ThreadDemo2020��9��22��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������3:57:01
 */
public class ThreadDemo {

	/**
	 * Constructor
	 */
	public ThreadDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��22������3:57:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadTest1 t1 = new ThreadTest1();
		new Thread(t1).start();
		new Thread(t1).start();
		System.out.println(t1.call());
	}
}
/**
 * @class_name:ThreadTest12020��9��22��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������4:01:13
 */
class ThreadTest1 implements Runnable{
	private int x;
	private int y;
	public synchronized void run(){
		for(int i=0;i<4;i++){
			x++;
			y++;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("�̳߳�����");
			}
			System.out.println(Thread.currentThread().getName()+" x="+x+",y="+y+" "+i);
		}
	}
	/**
	 * @Title: call
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��22������4:01:04
	 */
	public synchronized String call(){
		String name = Thread.currentThread().getName();
		return "Hello "+name;
	}
}
