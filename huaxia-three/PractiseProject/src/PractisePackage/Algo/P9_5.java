/**
 * Title: P9_5.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��17������2:45:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P9_52020��8��17��
 * @comments:շת������������Լ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��17������2:45:03
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
	 *@Date: 2020��8��17������2:45:03
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a,b,c;
		System.out.printf("����������������");
		Scanner input = new Scanner(System.in);
		a = input.nextInt();
		b = input.nextInt();
		c = gcd(a,b);
		System.out.printf("%d��%d�����Լ����%d\n", a,b,gcd(a,b));
	}
	/**
	 * @Title: gcd
	 *@Description: TODO շת������������Լ��
	 *@param a
	 *@param b
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��17������2:47:10
	 */
	public static int gcd(int a,int b)
	{
		int m,n,r;
		if(a>b)	 //m ����ϴ�����n�����С��
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
