/**
 * Title: P3_2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日上午10:48:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P3_22020年8月10日
 * @comments:地推算法求解兔子的规律
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日上午10:48:07
 */
public class P3_2 {

	/**
	 * Constructor
	 */
	public P3_2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午10:48:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("递推算法求解兔子产仔问题！");
		System.out.println("请输入时间：");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int num = fibonacci(n);
		System.out.println("经过"+n+"个月的时间，共能繁殖成"+num+"对兔子！");
	}
	/**
	 * @Title: fibonacci
	 *@Description: TODO 计算fibnoacci数列
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午10:50:47
	 */
	public static int fibonacci(int n)
	{
		int t1,t2;
		if(n == 1 || n==2)
			return 1;
		else
		{
			t1 = fibonacci(n-1);
			t2 = fibonacci(n-2);
			return t1+t2;
		}
	}
}
