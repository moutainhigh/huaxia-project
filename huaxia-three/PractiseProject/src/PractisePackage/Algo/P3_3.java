/**
 * Title: P3_3.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日上午11:02:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P3_32020年8月10日
 * @comments: 递归求阶乘
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日上午11:02:53
 */
public class P3_3 {

	/**
	 * Constructor
	 */
	public P3_3() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午11:02:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		System.out.println("请输入要求阶乘的一个整数：");
		Scanner sc = new Scanner(System.in);
		i = sc.nextInt();
		System.out.println(i+"的阶乘结果为："+fact(i));
	}
	/**
	 * @Title: fact
	 *@Description: TODO 阶乘函数
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午11:03:57
	 */
	private static  long fact(int n){
		if(n <= 1)
			return  1;
		else 
			return n* fact(n-1);
	}
}
