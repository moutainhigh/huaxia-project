/**
 * Title: SecondThread.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��22������10:39:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.CreayJava;

/**
 * @class_name:SecondThread2020��1��22��
 * @comments: ����runnable�ӿ�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��22������10:39:47
 */
public class SecondThread implements Runnable {
	private int i;
	/**
	 * 
	 */
	public SecondThread() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;i<100;i++)
		{
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��22������10:39:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++)
		{
			System.out.println(Thread.currentThread().getName()+" "+i);
			if(i == 20)
			{
				SecondThread st = new SecondThread();
				new FirstThread(st,"���߳�1").start();
				new FirstThread(st,"���߳�1").start();
			}
		}
	}

}
