/**
 * Title: EnumerationDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������3:23:40
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @class_name:EnumerationDemo2020��9��11��
 * @comments:Vector ���ϵ�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������3:23:40
 */
public class EnumerationDemo {

	/**
	 * Constructor
	 */
	public EnumerationDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������3:23:40
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<String> all = new Vector<String>();
		all.add("Hello");
		all.add("world");
		all.add("java");
		Enumeration<String> enu = all.elements();
		while(enu.hasMoreElements()){
			String str = enu.nextElement();
			System.out.println(str);
		}
	}

}
