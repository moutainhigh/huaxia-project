/**
 * Title: StartDead.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��22������11:17:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.CreayJava;

/**
 * @class_name:StartDead2020��1��22��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��22������11:17:54
 */
public class StartDead extends Thread {
	private int i;
	/**
	 * ��дrun����
	 */
	public void run()
	{
		for(;i<100;i++)
		{
			System.out.println(getName()+ " "+i);
		}
	}
	/**
	 * 
	 */
	public StartDead() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public StartDead(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public StartDead(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public StartDead(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public StartDead(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public StartDead(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public StartDead(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public StartDead(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��22������11:17:54
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�����̶߳���
		StartDead sd =new StartDead();
		for(int i=0;i<300;i++)
		{
			System.out.println(Thread.currentThread().getName()+" "+i);
			if(i == 20)
			{
				sd.start();
				System.out.println(sd.isAlive());
			}
			//���̴߳����½�����������״̬ʱ��isAlive��������false
			//��i>20ʱ�򣬸��߳��Ѿ��������ˣ����sd.isAliveΪ��ʱ���Ǿ�������״̬
			//��������̲߳����ٴ�����
			if(i > 20 && !sd.isAlive())
			{
				//��ͼ�ٴ��������߳�
				sd.start();
			}
		}
	}
}
