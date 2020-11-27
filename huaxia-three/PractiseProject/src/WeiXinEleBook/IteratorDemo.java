/**
 * Title: IteratorDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午3:02:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @class_name:IteratorDemo2020年9月11日
 * @comments:迭代器的使用，iterator迭代器的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午3:02:04
 */
public class IteratorDemo {

	/**
	 * Constructor
	 */
	public IteratorDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午3:02:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> al = new ArrayList<String>();
		al.add("Welcome");
		al.add("to");
		al.add("HAUT");
		System.out.print("a1中的内容是：");
		Iterator<String> itr = al.iterator();
		while(itr.hasNext()){
			Object element = itr.next();
			System.out.print(element+" ");
		}
		System.out.println();
	}

}
