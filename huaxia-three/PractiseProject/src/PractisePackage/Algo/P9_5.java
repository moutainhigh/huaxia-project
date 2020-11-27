/**
 * Title: P9_5.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日下午2:45:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P9_52020年8月17日
 * @comments:辗转相除法计算最大公约数
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日下午2:45:03
 */
public class P9_5 {

	/**
	 * Constructor
	 */
	public P9_5() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午2:45:03
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a,b,c;
		System.out.printf("输入两个正整数：");
		Scanner input = new Scanner(System.in);
		a = input.nextInt();
		b = input.nextInt();
		c = gcd(a,b);
		System.out.printf("%d和%d的最大公约数：%d\n", a,b,gcd(a,b));
	}
	/**
	 * @Title: gcd
	 *@Description: TODO 辗转相除法计算最大公约数
	 *@param a
	 *@param b
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午2:47:10
	 */
	public static int gcd(int a,int b)
	{
		int m,n,r;
		if(a>b)	 //m 保存较大数，n保存较小数
		{
			m = a;
			n = b;
		}
		else
		{
			m = b;
			n = a;
		}
		r = m%n;
		while(r != 0)
		{
			m = n;
			n = r;
			r = m%n;
		}
		return n;
	}
}	
