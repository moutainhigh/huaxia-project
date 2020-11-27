/**
 * Title: Fact2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月7日下午12:38:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:Fact22020年8月7日
 * @comments: 
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月7日下午12:38:47
 */
public class Fact2 {

	/**
	 * Constructor
	 */
	public Fact2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午12:38:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		System.out.println("请输入要求阶乘的一个整数：");
		Scanner sc = new Scanner(System.in);
		i = sc.nextInt();
		System.out.println(i+"的阶乘结果为"+fact(i));
	}
	/**
	 * @Title: fact
	 *@Description: TODO
	 *@param n 阶乘递归函数
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午12:39:45
	 */
	public static int fact(int n){
		if(n<1)
			return 1;
		else
			return n*fact(n-1);
	}
}
