/**
 * Title: Goldbach1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日下午3:26:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:Goldbach12020年8月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日下午3:26:15
 */
public class Goldbach1 {

	/**
	 * Constructor
	 */
	public Goldbach1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午3:26:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n,i,j,flag;
		System.out.printf("请输入一个最大范围n(n>=6):"	);
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		if(n<6)
		{
			System.out.println("数据输入有误");
		}
		for(i = 6;i<= n;i+=2)
		{
			flag = 1;
			for(j = 2;j<=i/2;j++)
			{
				if(j%2 == 0 ||((i-j)%2 == 0))
				{
					continue;
				}
				if(PrimeNum(j) == 1 && PrimeNum(i-j) == 1)
				{
					System.out.printf("%d = %d + %d\n",i,j,i-j);
					flag = 0;
					break;
				}
			}
			if(1 == flag)
			{
				System.out.println("找到一个不符合要求的偶数！");
			}
		}
	}
	/**
	 * @Title: isPrime
	 *@Description: TODO 判断是否为素数
	 *@param a
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午3:03:00
	 */
	public static int isPrime(int a)
	{
		int i;
		for(i = 2;i<a ;i++)
		{
			if( a % i == 0)
				return 0;
		}
		return 1;
	}
	/**
	 * @Title: PrimeNum
	 *@Description: TODO判断是否为素数
	 *@param i
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午3:42:28
	 */
	public static int PrimeNum(int i)
	{
		int j ,flag = 1;
		for(j = 2;j*j < i;j++)
		{
			if(i % j == 0)
				flag = 0;
			break;
		}
		return flag;
	}
	
}	
