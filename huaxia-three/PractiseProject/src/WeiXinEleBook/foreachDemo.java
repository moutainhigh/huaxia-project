/**
 * Title: foreachDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������3:28:59
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.ArrayList;
import java.util.List;

/**
 * @class_name:foreachDemo2020��9��11��
 * @comments: ArrayList ��ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������3:28:59
 */
public class foreachDemo {

	/**
	 * Constructor
	 */
	public foreachDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������3:28:59
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> all = new ArrayList<String>();
		all.add("Hello");
		all.add("world");
		all.add("Java! ");
		for(String str:all){
			System.out.println(str);
		}
	}

}
