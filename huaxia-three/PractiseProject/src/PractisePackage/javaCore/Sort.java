/**
 * Title: Sort.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月21日下午3:39:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @class_name:Sort2020年8月21日
 * @comments:使用集合进行排序
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月21日下午3:39:12
 */
public class Sort {
	
	/**
	 * Constructor
	 */
	public Sort() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月21日下午3:39:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		String[] strArr = str.split(" ");
		List<String> list = Arrays.asList(strArr);
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
	}

}
