/**
 * Title: TestThreadList.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年11月4日上午11:18:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @class_name:TestThreadList2020年11月4日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年11月4日上午11:18:24
 */
public class TestThreadList {
	static int semaphore = 0;
	/**
	 * Constructor
	 */
	public TestThreadList() {
		// TODO Auto-generated constructor stub
	}
	public synchronized void addSemaphore(){
		this.semaphore ++;
	}
	public synchronized void subSemaphore(){
		this.semaphore --;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年11月4日上午11:18:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ExecutorService executors = Executors.newFixedThreadPool(3);
		int corePoolSize = 3;
		int maximumPoolSize = 3;
		int keepAliveTime = 5;
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
		RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

		ExecutorService es = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
				TimeUnit.SECONDS, workQueue, handler);
		
		
//		ExecutorService es = Executors.newFixedThreadPool(3);
		List<String> list =  Collections.synchronizedList(new ArrayList<String>()); 
		int total  = 0;
		int row = 0;
		while(row < 30000001){
			if(total >= 3000){
				List<String> listtemp = list;
//				System.out.println("list.size="+list.size());
//				System.out.println("listtemp.size="+listtemp.size());
				es.submit(new LowRiskThread(listtemp));
				 list =  Collections.synchronizedList(new ArrayList<String>()); 
//				System.out.println("list.size="+list.size());
//				System.out.println("listtemp.size="+listtemp.size());
				total  = 0;
			}
			list.add(""+row);
			total++;
			row ++;
		}
		es.shutdown();
		while(true){
			if(es.isTerminated()){
				System.out.println("over");
				break;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.err.println("list.get(0)="+list.get(0));
		System.err.println("list.size()="+list.size());
		
	}
	/**
	 * 
	 * @class_name:LowRiskThread2020年11月4日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年11月4日上午11:25:32
	 */
 static	class LowRiskThread implements Runnable{
		List<String> list;
		public LowRiskThread(List<String> list){
			this.list =list;
		}
		@Override
		public void run() {
			try{
//				for(int i = 0;i<list.size();i++){
//					System.out.println("list.get(0)="+list.get(0));
					System.out.println(Thread.currentThread().getName()+",list="+list.get(0));
//					Thread.sleep(1);
//				}
//				System.out.println("LowRiskThread.list.size="+list.size());
				list.clear();
//				System.out.println("totalCount="+totalCount);
//				System.out.println("--LowRiskThread.list.size="+list.size());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

