/**
 * Title: TreeSetTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��10������11:29:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter9;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @class_name:TreeSetTest2020��1��10��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��10������11:29:22
 */
public class TreeSetTest {
	
	/**
	 * 
	 */
	public TreeSetTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��10������11:29:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedSet<Item> parts = new TreeSet<>();
		parts.add(new Item("Toaster",1234));
		parts.add(new Item("Widget",4562));
		parts.add(new Item("Modem",9912));
//		NavigableSet<Item> sortByDescription = new TreeSet<>(Comparator.comparing(Item::getDescription));
		System.out.println(parts);
		
	}

}
