/**
 * Title: p10_15.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月13日上午10:12:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:p10_152020年8月13日
 * @comments:兔子问题，生物学的增值问题
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月13日上午10:12:07
 */
public class p10_15 {
	/**
	 * @Title: Fibonacci
	 *@Description: TODO 计算fibonacci数列
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日上午10:14:18
	 */
	static int Fibonacci(int n){
		int t1,t2;
		if(n==1 || n ==2)
		{
			return 1;
		}
		else{
			t1 = Fibonacci(n-1);
			t2 = Fibonacci(n-2);
			return t1+t2;
		}
	}
	/**
	 * Constructor
	 */
	public p10_15() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月13日上午10:12:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n,num;
		System.out.println("兔子产仔问题求解!\n");
		System.out.println("请先时间：");
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		num = Fibonacci(n);
		System.out.println("经过"+n+"个月的时间，共能繁殖成"+num+"对兔子！	");
	}
}
