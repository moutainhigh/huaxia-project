/**
 * Title: Genericwildcard.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日下午2:33:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @class_name:Genericwildcard2020年9月18日
 * @comments: 使用通配符的泛型
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日下午2:33:05
 */
public class Genericwildcard {
	/**
	 * @Title: getMiddle
	 *@Description: TODO
	 *@return 
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午2:34:24
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
	 *@Date: 2020年9月18日下午2:33:05
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(1);
		ints.add(2);
		ints.add(3);
		System.out.print("整型列表的元素：");
		System.out.println(Arrays.toString(ints.toArray()));
		System.out.println("整型列表的中间数"+getMiddle(ints));
		List<Double> doubles = new ArrayList<Double>();
		doubles.add(1.1);
		doubles.add(2.2);
		doubles.add(3.3);
		System.out.print("浮点列表的元素：");
		System.out.println(Arrays.toString(doubles.toArray()));
		System.out.println("浮点列表的中间数"+getMiddle(doubles));
	}

}
