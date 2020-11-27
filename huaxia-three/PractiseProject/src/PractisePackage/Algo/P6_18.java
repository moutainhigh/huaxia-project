/**
 * Title: P6_18.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午4:18:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P6_182020年8月10日
 * @comments: 循环计算阶乘
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午4:18:12
 */
public class P6_18 {

	/**
	 * Constructor
	 */
	public P6_18() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午4:18:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i ;
		System.out.print("请输入要求阶乘的一个整数：");
		Scanner input = new Scanner(System.in);
		i = input.nextInt();
		System.out.println(i+"的阶乘结果为："+fact(i));
	}
	/**
	 * @Title: fact
	 *@Description: TODO 循环计算阶乘
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午4:19:16
	 */
	public static long fact(int n)
	{
		int i;
		long result = 1;
		for(i =1;i<=n;i++)
		{
			result *= i;
		}
		return result;
	}
}
