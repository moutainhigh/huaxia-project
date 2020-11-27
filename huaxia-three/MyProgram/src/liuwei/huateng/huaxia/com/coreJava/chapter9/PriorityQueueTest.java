/**
 * Title: PriorityQueueTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月10日下午2:14:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter9;

import java.util.Date;
import java.util.PriorityQueue;

/**
 * @class_name:PriorityQueueTest2020年1月10日
 * @comments:
 * @param: 优先级队列就是顺序的
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月10日下午2:14:56
 */
public class PriorityQueueTest {

	/**
	 * 
	 */
	public PriorityQueueTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月10日下午2:14:56
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<Date> pq =new PriorityQueue<>();
		pq.add(new Date(1906,12,9));
		pq.add(new Date(1815,12,10));
		pq.add(new Date(1910,6,22));
		System.out.println("Iterating over elements");
		for(Date date :pq){
			System.out.println(date);
		}
		System.out.println("Removing elements...");
		while(!pq.isEmpty())	
			System.out.println(pq.remove());
	}

}
