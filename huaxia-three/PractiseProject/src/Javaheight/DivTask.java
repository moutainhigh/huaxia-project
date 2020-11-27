/**
 * Title: DivTask.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月15日上午10:44:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @class_name:DivTask2020年10月15日
 * @comments: 线程池没有报错
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月15日上午10:44:33
 */
public class DivTask implements Runnable {
	int a,b;
	/**
	 * Constructor
	 */
	public DivTask() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 */
	public DivTask(int a,int b) {
		// TODO Auto-generated constructor stub
		this.a = a;
		this.b = b;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		double re = a/b;
		System.out.println(re);
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月15日上午10:44:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor pools = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0L,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
		for(int i=0;i<5;i++){
			Future re =pools.submit(new DivTask(100,i));
			try {
				re.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
