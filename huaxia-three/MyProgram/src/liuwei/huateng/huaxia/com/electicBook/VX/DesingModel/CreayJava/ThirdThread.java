/**
 * Title: ThirdThread.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月22日上午10:50:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.CreayJava;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @class_name:ThirdThread2020年1月22日
 * @comments: Callable方法容许声明抛出异常和获取返回值
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月22日上午10:50:04
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
	 *@Date: 2020年1月22日上午10:50:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThirdThread rt = new ThirdThread();
		//使用FutureTask来包装Callable对象
		FutureTask<Integer> task = new FutureTask<Integer>(rt);
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
			if(i == 20)
			{
				//实质还是以Callable对象来创建并启动线程
				new Thread(task,"有返回值的线程").start();
			}
		}
		try{
			System.out.println("子线程的返回值："+task.get());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 实现call()，方法，作为线程体
	 */
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		for(;i<100;i++)
		{
			System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
		}
		//call方法可以有返回值
		return i;
	}

}
