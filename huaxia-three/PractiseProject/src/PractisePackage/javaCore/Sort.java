/**
 * Title: Sort.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��21������3:39:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @class_name:Sort2020��8��21��
 * @comments:ʹ�ü��Ͻ�������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��21������3:39:12
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
	 *@Date: 2020��8��21������3:39:12
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
