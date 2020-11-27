/**
 * Title: P6_20.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午4:30:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P6_202020年8月10日
 * @comments:割圆术计算pi
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午4:30:50
 */
public class P6_20 {

	/**
	 * Constructor
	 */
	public P6_20() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午4:30:50
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		System.out.println("请输入切割次数：");
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		cyclotomic(n);
	}
	/**
	 * @Title: cyclotomic
	 *@Description: TODO 割圆术计算pi
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午4:31:28
	 */
	public static void cyclotomic(int n)
	{
		int i,s;
		double k,len;
		i =0;
		k=3.0;
		len = 1.0;
		s = 6;
		while(i<= n)
		{
			System.out.printf("第%2d次切割，为正%5d边形，PI=%.24f\n",i,s,k*Math.sqrt(len));
			s*=2;
			len = 2-Math.sqrt(4-len);
			i++;
			k*=2.0;
		}
	}
}
