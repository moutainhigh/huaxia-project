/**
 * Title: Odd_even.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日下午5:19:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:Odd_even2020年9月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日下午5:19:51
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
	 *@Date: 2020年9月21日下午5:19:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入一个整数：");
		long number = scan.nextLong();
		String check= (number % 2 == 0)?"这个数字是偶数":"这个数字是奇数";
		System.out.println(check);
	}

}
