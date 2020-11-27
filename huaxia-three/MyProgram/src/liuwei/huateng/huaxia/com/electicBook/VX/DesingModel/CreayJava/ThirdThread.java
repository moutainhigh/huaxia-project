/**
 * Title: ThirdThread.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��22������10:50:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.CreayJava;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @class_name:ThirdThread2020��1��22��
 * @comments: Callable�������������׳��쳣�ͻ�ȡ����ֵ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��22������10:50:04
 */
public class ThirdThread implements Callable<Integer> {

	/**
	 * 
	 */
	public ThirdThread() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��22������10:50:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThirdThread rt = new ThirdThread();
		//ʹ��FutureTask����װCallable����
		FutureTask<Integer> task = new FutureTask<Integer>(rt);
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+" ��ѭ������i��ֵ"+i);
			if(i == 20)
			{
				//ʵ�ʻ�����Callable�����������������߳�
				new Thread(task,"�з���ֵ���߳�").start();
			}
		}
		try{
			System.out.println("���̵߳ķ���ֵ��"+task.get());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * ʵ��call()����������Ϊ�߳���
	 */
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		for(;i<100;i++)
		{
			System.out.println(Thread.currentThread().getName()+" ��ѭ������i��ֵ"+i);
		}
		//call���������з���ֵ
		return i;
	}

}
