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
 * @comments: 线程池没有报错,execute执行会输出错误，submit不会
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月15日上午10:44:33
 */
public class DivTask2 implements Runnable {
	int a,b;
	/**
	 * Constructor
	 */
	public DivTask2() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 */
	public DivTask2(int a,int b) {
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
			pools.execute(new DivTask2(100,i));
		}
	}
}
