/**
 * Title: ThreeSetDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������2:57:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.TreeSet;

/**
 * @class_name:ThreeSetDemo2020��9��11��
 * @comments:TreeSet��ʹ�ã����ͽṹ����˳��洢
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������2:57:38
 */
public class ThreeSetDemo {

	/**
	 * Constructor
	 */
	public ThreeSetDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������2:57:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet<String> ts = new TreeSet<String>();
		ts.add("C");
		ts.add("A");
		ts.add("B");
		ts.add("E");
		ts.add("F");
		ts.add("D");
		System.out.println(ts);
	}

}
