/**
 * Title: Odd_even.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��21������5:19:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:Odd_even2020��9��21��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��21������5:19:51
 */
public class Odd_even {

	/**
	 * Constructor
	 */
	public Odd_even() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��21������5:19:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("������һ��������");
		long number = scan.nextLong();
		String check= (number % 2 == 0)?"���������ż��":"�������������";
		System.out.println(check);
	}

}
