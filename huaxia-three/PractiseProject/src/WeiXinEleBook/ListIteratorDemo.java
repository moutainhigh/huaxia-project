/**
 * Title: ListIteratorDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午3:13:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @class_name:ListIteratorDemo2020年9月11日
 * @comments: ListIterator的更新和反向输出
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午3:13:29
 */
public class ListIteratorDemo {

	/**
	 * Constructor
	 */
	public ListIteratorDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午3:13:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> al = new ArrayList<String>();
		al.add("Welcome");
		al.add("to");
		al.add("HAUT");
		ListIterator<String> litr = al.listIterator();
		while(litr.hasNext()){
			Object element = litr.next();
			litr.set(element + "+");
		}
		System.out.println("将列表反向输出");
		while(litr.hasPrevious()){
			Object element = litr.previous();
			System.out.print(element+" ");
		}
		System.out.println();
	}
}
