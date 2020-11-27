/**
 * Title: IteratorDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日下午2:03:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.IneratorPattern;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @class_name:IteratorDemo2020年1月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日下午2:03:53
 */
public class IteratorDemo {

	/**
	 * 
	 */
	public IteratorDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月7日下午2:03:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//定义一个向量集合
		ArrayList list = new ArrayList();
		list.add("序列1");
		list.add("序列2");
		list.add("序列3");
		//定义一个栈
		Stack stack = new Stack();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		//遍历各个集合
		java.util.Iterator iterator = list.iterator();
		System.out.println("遍历序列中的元素是：");
		show(iterator);
		System.out.println("\n\n遍历栈中中的元素是：");
		show(stack.iterator());
	}	
	/**
	 * @Title: show
	 *@Description: TODO 集合的遍历
	 *@param iterator
	 *@author: LiuWei
	 *@Date: 2020年1月7日下午2:06:03
	 */
	public static void show(java.util.Iterator iterator){
		while(iterator.hasNext()){
			System.out.print(iterator.next()+" ");
		}
	}
}
