/**
 * Title: ListIteratorDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������3:13:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @class_name:ListIteratorDemo2020��9��11��
 * @comments: ListIterator�ĸ��ºͷ������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������3:13:29
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
	 *@Date: 2020��9��11������3:13:29
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
		System.out.println("���б������");
		while(litr.hasPrevious()){
			Object element = litr.previous();
			System.out.print(element+" ");
		}
		System.out.println();
	}
}
