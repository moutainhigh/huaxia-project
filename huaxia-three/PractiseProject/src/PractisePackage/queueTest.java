/**
 * Title: queueTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月6日下午3:55:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @class_name:queueTest2020年8月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午3:55:01
 */
public class queueTest {

	/**
	 * Constructor
	 */
	public queueTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午3:55:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		queueTst();
		dequeueTst();
	}
	/**
	 * @Title: queueTst 队列的运行 先进先出
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午3:57:49
	 */
	public static void  queueTst(){
		Queue<String> queue = new LinkedList<>();
		queue.offer("a");
		queue.offer("b");
		queue.offer("c");
		while(queue.peek() != null){
			System.out.println(queue.poll());
		}
	}
	/**
	 * @Title: dequeueTst 栈的测试 先进后出
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午4:00:21
	 */
	public static void dequeueTst(){
		Deque<String> stack = new LinkedList<>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		while(stack.peek() != null){
			System.out.println(stack.pop());
		}
	}
}
