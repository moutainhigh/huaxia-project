/**
 * Title: TestDataStructs.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月19日上午10:01:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

/**
 * @class_name:TestDataStructs2020年8月19日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月19日上午10:01:06
 */
public class TestDataStructs {
	/**
	 * @Title: ArrayCase 数组的使用
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年8月19日上午10:05:55
	 */
	public static void ArrayCase(){
		String[] strs = new String[]{"a","b","c"};
		for(int i=0;i<strs.length;i++){
			System.out.print(strs[i] + ", ");
		}
		System.out.println();
	}
	/**
	 * @Title: ArrayCase2
	 *@Description: TODO 数组的使用案例，Arrays的方法使用
	 *@author: LiuWei
	 *@Date: 2020年8月19日上午10:10:32
	 */
	public static void ArrayCase2(){
		int[] ary = new int[]{3,2,5,1};
		System.out.println(Arrays.toString(ary));
		Arrays.sort(ary);
		System.out.println(Arrays.toString(ary));
		int index = Arrays.binarySearch(ary, 3);
		System.out.println(index);
		List<int[]> asList = Arrays.asList(ary);
		System.out.println(asList.size());
	}
	/**
	 * @Title: listCase
	 *@Description: TODO ArrayList的使用，List的使用，size，get，set，remove，toarray。
	 *@author: LiuWei
	 *@Date: 2020年8月19日上午10:21:33
	 */
	public static void listCase(){
		List<String> list =new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println(list.toString());
		int size = list.size();
		System.out.println(size);
		String string = list.get(0);
		System.out.println(string);
		list.set(1, "B");
		System.out.println(list.toString());
		list.remove(1);
		System.out.println(list.toString());
	}
	/**
	 * @Title: mapcase mapcase的使用
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年8月19日上午10:44:33
	 */
	public static void mapcase(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("壹", "123");
		map.put("贰", "456");
		map.put("叁", "789");
		System.out.println(map.toString());
		int size = map.size();
		System.out.println(size);
		String getStr = map.get("壹");
		System.out.println(getStr);
		Set<String> keySet = map.keySet();
		System.out.println(keySet);
		Collection<String> values = map.values();
		System.out.println(values);
		map.remove("叁");
		System.out.println(map.toString());
		Set<Entry<String,String>> entrySet = map.entrySet();
		System.out.println(entrySet);
	}
	/**
	 * @Title: queueCase
	 *@Description: TODO 队列的使用
	 *@author: LiuWei
	 *@Date: 2020年8月19日上午10:49:07
	 */
	public static void queueCase(){
		Queue<String> queue = new LinkedList<String>();
		queue.offer("q");
		queue.offer("u");
		queue.offer("e");
		System.out.println(queue.toString());
		String peek = queue.peek();
		System.out.println(peek);
		while(queue.peek() != null){
			System.out.print(queue.peek()+",");
			queue.poll();
		}
		System.out.println(queue.toString());
	}
	/**
	 * @Title: dequecase
	 *@Description: TODO 栈的使用
	 *@author: LiuWei
	 *@Date: 2020年8月19日上午10:54:51
	 */
	public static void dequecase(){
		Deque<String> deque = new LinkedList<String>();
		deque.push("d");
		deque.push("e");
		deque.push("q");
		System.out.println(deque.toString());
		String peek= deque.peek();
		System.out.println(peek);
		while(deque.peek() != null){
			System.out.print(deque.peek()+",");
			deque.pop();
		}
		System.out.println(deque.toString());
	}
	public static void dequecase2(){
		Deque<String> deque = (Deque<String>) new ArrayList<String>();
		deque.push("d");
		deque.push("e");
		deque.push("q");
		System.out.println(deque.toString());
		String peek= deque.peek();
		System.out.println(peek);
		while(deque.peek() != null){
			System.out.print(deque.peek()+",");
			deque.pop();
		}
		System.out.println(deque.toString());
	}
	/**
	 * Constructor
	 */
	public TestDataStructs() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月19日上午10:01:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dequecase2();
	}
}
