package ThinkInJava;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
/**
 * 测试队列的使用
 * 使用的Queue的linkedlist对象，该对象可以实现相应的队列接口
 * @author liuwei
 *
 */
public class QueueDemo {
	public static void printQ(Queue queue){
		while(queue.peek() != null)
			System.out.print(queue.remove()+" ");
		System.out.println();
	}
	public static void main(String args[]){
		Queue<Integer> queue = new LinkedList<Integer>();
		Random rand = new Random(47);
		for(int i=0;i<10;i++){
			queue.offer(rand.nextInt(i+10));
		}
		printQ(queue);
		Queue<Character> qc= new LinkedList<Character>();
		for(char c :"Brontosaureus".toCharArray()){
			qc.offer(c);
		}
		printQ(qc);
	}
}
