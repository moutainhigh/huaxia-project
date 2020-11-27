/**
 * Title: Sorter.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月17日下午2:14:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @class_name:Sorter2020年9月17日
 * @comments: 利用集合工具对数字排序
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月17日下午2:14:20
 */
public class Sorter {
	/**
	 * @Title: sort
	 *@Description: TODO
	 *@param array
	 *@author: LiuWei
	 *@Date: 2020年9月17日下午2:14:44
	 */
	public static void sort(Integer[] array){
		List<Integer> list = Arrays.asList(array);
		Collections.sort(list);
		for(int i = 0;i<list.size();i++){
			array[i] = list.get(i);
		}
		print(array);
	}
	/**
	 * @Title: print
	 *@Description: TODO
	 *@param array
	 *@author: LiuWei
	 *@Date: 2020年9月17日下午2:17:14
	 */
	public static void print(Integer[] array){
		for(Integer d:array){
			System.out.print(d+" ");
		}
		System.out.println();
	}
	/**
	 * Constructor
	 */
	public Sorter() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月17日下午2:14:20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] array = new Integer[]{95,77,48,69,82};
		sort(array);
	}

}
