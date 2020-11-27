/**
 * Title: IteratorDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������2:03:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.IneratorPattern;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @class_name:IteratorDemo2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������2:03:53
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
	 *@Date: 2020��1��7������2:03:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����һ����������
		ArrayList list = new ArrayList();
		list.add("����1");
		list.add("����2");
		list.add("����3");
		//����һ��ջ
		Stack stack = new Stack();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		//������������
		java.util.Iterator iterator = list.iterator();
		System.out.println("���������е�Ԫ���ǣ�");
		show(iterator);
		System.out.println("\n\n����ջ���е�Ԫ���ǣ�");
		show(stack.iterator());
	}	
	/**
	 * @Title: show
	 *@Description: TODO ���ϵı���
	 *@param iterator
	 *@author: LiuWei
	 *@Date: 2020��1��7������2:06:03
	 */
	public static void show(java.util.Iterator iterator){
		while(iterator.hasNext()){
			System.out.print(iterator.next()+" ");
		}
	}
}
