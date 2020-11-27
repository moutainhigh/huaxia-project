/**
 * Title: Genericwildcard.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������2:33:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @class_name:Genericwildcard2020��9��18��
 * @comments: ʹ��ͨ����ķ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������2:33:05
 */
public class Genericwildcard {
	/**
	 * @Title: getMiddle
	 *@Description: TODO
	 *@return 
	 *@author: LiuWei
	 *@Date: 2020��9��18������2:34:24
	 */
	public static  Object getMiddle(List<? extends Number> list){
		return list.get(list.size()/2);
	}
	/**
	 * Constructor
	 */
	public Genericwildcard() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��18������2:33:05
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(1);
		ints.add(2);
		ints.add(3);
		System.out.print("�����б��Ԫ�أ�");
		System.out.println(Arrays.toString(ints.toArray()));
		System.out.println("�����б���м���"+getMiddle(ints));
		List<Double> doubles = new ArrayList<Double>();
		doubles.add(1.1);
		doubles.add(2.2);
		doubles.add(3.3);
		System.out.print("�����б��Ԫ�أ�");
		System.out.println(Arrays.toString(doubles.toArray()));
		System.out.println("�����б���м���"+getMiddle(doubles));
	}

}
