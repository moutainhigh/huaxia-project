/**
 * Title: HashSetDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������2:53:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.HashSet;

/**
 * @class_name:HashSetDemo2020��9��11��
 * @comments: hashset���͵�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������2:53:33
 */
public class HashSetDemo {

	/**
	 * Constructor
	 */
	public HashSetDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������2:53:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> hs = new HashSet<String>();
		hs.add("B");
		hs.add("A");
		hs.add("D");
		hs.add("E");
		hs.add("C");
		hs.add("F");
		System.out.println(hs);
	}

}
