/**
 * Title: IteratorDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������3:02:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @class_name:IteratorDemo2020��9��11��
 * @comments:��������ʹ�ã�iterator��������ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������3:02:04
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
	 *@Date: 2020��9��11������3:02:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> al = new ArrayList<String>();
		al.add("Welcome");
		al.add("to");
		al.add("HAUT");
		System.out.print("a1�е������ǣ�");
		Iterator<String> itr = al.iterator();
		while(itr.hasNext()){
			Object element = itr.next();
			System.out.print(element+" ");
		}
		System.out.println();
	}

}
