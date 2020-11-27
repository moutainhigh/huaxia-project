/**
 * Title: LinkedListTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月31日下午4:59:59
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter9;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @class_name:LinkedListTest2019年12月31日
 * @comments:测试迭代器的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日下午4:59:59
 */
public class LinkedListTest {
	/**
	 * 
	 */
	public LinkedListTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:59:59
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> a = new LinkedList<>();
		a.add("Amy");
		a.add("Carl");
		a.add("Erica");
		
		List<String> b = new LinkedList<>();
		b.add("Bob");
		b.add("Doug");
		b.add("Frances");
		b.add("Gloria");
		
		ListIterator<String> aIter = a.listIterator();
		Iterator<String> bIter = b.iterator();
		while(bIter.hasNext())
		{
			if(aIter.hasNext())aIter.next();
			aIter.add(bIter.next());
		}
		System.out.println(a);
		bIter = b.iterator();
		while(bIter.hasNext())
		{
			bIter.next();
			if(bIter.hasNext())
			{
				bIter.next();
				bIter.remove();
			}
		}
		System.out.println(b);
		a.removeAll(b);
		System.out.println(a);
	}

}
