/**
 * Title: MyCallable.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月24日上午9:57:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @class_name:MyCallable2020年9月24日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月24日上午9:57:06
 */
public class MyCallable implements Callable<String> {
	private String name;
	
	/**
	 * Constructor
	 */
	public MyCallable(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月24日上午9:57:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stubz
		ExecutorService pool = Executors.newFixedThreadPool(5);
		List<Future> list = new ArrayList<Future>();
		for(int i=0;i<5;i++){
			Callable c= new MyCallable(i+" ");
			Future future = pool.submit(c);
			System.out.println("submit a callable thread:"+i);
			list.add(future);
		}
		pool.shutdown();
		for(Future future:list){
			try {
				System.out.println("get the result from callable thread:"+future.get().toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return name;
	}

}
