/**
 * Title: P10_18.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月13日上午10:41:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P10_182020年8月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月13日上午10:41:27
 */
public class P10_18 {
	/**
	 * @Title: threeball
	 *@Description: TODO threeball 三色球问题
	 *@param red
	 *@param yellow
	 *@param green
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020年8月13日上午10:47:45
	 */
	public static void threeball(int red,int yellow,int green,int n)
	{
		int i,j,k;
		System.out.printf("总共有如下几种可能！\n");
		System.out.printf("\t红球\t黄球\t绿球\n");
		for(i = 0;i<=3;i++){
			for(j=0;j<=3;j++)
			{
				for(k=0;k<=6;k++)
				{
					if(i+j+k == n)
					{
						System.out.printf("\t%d\t%d\t%d\n",i,j,k);
					}
				}
			}
		}
	}
	/**
	 * Constructor
	 */
	public P10_18() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月13日上午10:41:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int red,yellow,green;
		int n;
		System.out.printf("三色球问题求解！\n");
		System.out.printf("请先输入红球的数量为：");
		Scanner input = new Scanner(System.in);
		red = input.nextInt();
		System.out.printf("请先输入黄球的数量：");
		yellow = input.nextInt();
		System.out.printf("请先输入绿球的数量：");
		green = input.nextInt();
		System.out.printf("请先输入取出球的数量为：");
		n = input.nextInt();
		threeball(red,yellow,green,n);
	}

}
