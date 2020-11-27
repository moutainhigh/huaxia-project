/*package ThinkInJava;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import sun.nio.cs.Surrogate.Generator;
*//**
 * 进行队列的测试使用
 * @author liuwei
 *
 *//*
public class QueueBehavior {
	public static int count = 10;
	static <T> void test(Queue<T> queue,Gen gen){
		for(int i=0;i<count;i++){
			queue.offer((T) gen.next());
		}
		while(queue.peek() != null)
			System.out.print(queue.remove()+" ");
		System.out.println();
	}
	static class Gen extends Generator{
		String[] s=("one two three four five six serven eight nine ten").split(" ");
		int i ;
		public String next(){
			return s[i++];
		}
	}
	public static void main(String args[]){
		test(new LinkedList<String>(),new Gen());
		test(new PriorityQueue<String>(),new Gen());
		test(new LinkedBlockingQueue<String>(),new Gen());
		test(new ConcurrentLinkedQueue<String>(),new Gen());
	}
}
*/