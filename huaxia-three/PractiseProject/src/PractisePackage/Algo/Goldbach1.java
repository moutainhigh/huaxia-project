/**
 * Title: Goldbach1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��17������3:26:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:Goldbach12020��8��17��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��17������3:26:15
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
	 *@Date: 2020��8��17������3:26:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n,i,j,flag;
		System.out.printf("������һ�����Χn(n>=6):"	);
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		if(n<6)
		{
			System.out.println("������������");
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
				System.out.println("�ҵ�һ��������Ҫ���ż����");
			}
		}
	}
	/**
	 * @Title: isPrime
	 *@Description: TODO �ж��Ƿ�Ϊ����
	 *@param a
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��17������3:03:00
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
	 *@Description: TODO�ж��Ƿ�Ϊ����
	 *@param i
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��17������3:42:28
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
