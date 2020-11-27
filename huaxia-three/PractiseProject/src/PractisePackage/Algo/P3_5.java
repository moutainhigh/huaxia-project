/**
 * Title: P3_5.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������11:10:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P3_52020��8��10��
 * @comments:���ؿ����㷨����pi���㷨
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������11:10:04
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
	 *@Date: 2020��8��10������11:10:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		double PI;
		System.out.println("���ؿ��޸����㷨����pi");
		Scanner input = new Scanner(System.in);
		System.out.println("����������");
		n = input.nextInt();
		PI = MontePI(n);
		System.out.println("PI="+PI);
	}
	/**
	 * @Title: MontePI
	 *@Description: TODO �������ؿ����㷨����pi���㷨
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��10������11:10:55
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
