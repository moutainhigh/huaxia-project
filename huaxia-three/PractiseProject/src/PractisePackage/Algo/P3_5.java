/**
 * Title: P3_5.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日上午11:10:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P3_52020年8月10日
 * @comments:蒙特卡罗算法计算pi的算法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日上午11:10:04
 */
public class P3_5 {

	/**
	 * Constructor
	 */
	public P3_5() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午11:10:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		double PI;
		System.out.println("蒙特卡罗概率算法计算pi");
		Scanner input = new Scanner(System.in);
		System.out.println("输入点的数量");
		n = input.nextInt();
		PI = MontePI(n);
		System.out.println("PI="+PI);
	}
	/**
	 * @Title: MontePI
	 *@Description: TODO 根据蒙特卡罗算法计算pi的算法
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午11:10:55
	 */
	public static double MontePI(int n){
		double PI;
		double x,y;
		int i,sum;
		sum = 0;
		for(i = 1;i< n;i++)
		{
			x  = Math.random();
			y = Math.random();
			if((x*x+y*y) <= 1)
				sum++;
		}
		PI = 4.0*sum/n;
		return PI;
	}
}
