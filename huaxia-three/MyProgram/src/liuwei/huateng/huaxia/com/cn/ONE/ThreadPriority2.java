/**
 * Title: ThreadPriority2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��5������4:34:41
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

/**
 * @class_name:ThreadPriority22019��12��5��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��5������4:34:41
 */
public class ThreadPriority2 {

	/**
	 * 
	 */
	public ThreadPriority2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��5������4:34:41
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread();
		System.out.println("t1 priority "+t1.getPriority());
		Thread t2 = new Thread(
				new Runnable(){
					@Override
					public void run(){
						Thread t3 = new Thread();
						System.out.println("t3 priority "+t3.getPriority());
					}
				}
				);
	t2.setPriority(6);
	t2.start();
	System.out.println("t2 priority "+t2.getPriority());
	}

}
