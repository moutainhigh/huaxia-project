/**
 * Title: queueTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��6������3:55:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @class_name:queueTest2020��8��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������3:55:01
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
	 *@Date: 2020��8��6������3:55:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		queueTst();
		dequeueTst();
	}
	/**
	 * @Title: queueTst ���е����� �Ƚ��ȳ�
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��8��6������3:57:49
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
	 * @Title: dequeueTst ջ�Ĳ��� �Ƚ����
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��8��6������4:00:21
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
