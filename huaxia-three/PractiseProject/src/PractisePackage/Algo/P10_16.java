/**
 * Title: P10_16.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月13日上午10:21:57
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P10_162020年8月13日
 * @comments: 常胜将军问题 每次只要取5的倍数即可获胜
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月13日上午10:21:57
 */
public class P10_16 {
	static int computer,user,last;
	/**
	 * @Title: jiangjun
	 *@Description: TODO 常胜将军问题
	 *@author: LiuWei
	 *@Date: 2020年8月13日上午10:33:36
	 */
	static void jiangjun()
	{
		while(true)
		{
			System.out.printf("----------目前还有火柴%d根----------\n",last);
			System.out.printf("用户取火柴数量：");
			Scanner input = new Scanner(System.in);
			user = input.nextInt();
			if(user<1 || user>4 || user>last)
			{
				System.out.printf("你违规了，你取的火柴数有问题！\n\n");
				continue;
			}
			last = last - user;
			if(last == 0)
			{
				System.out.printf("\n用户取了最后一个火柴，因此计算机赢了！\n");
				break;
			}
			else
			{
				computer = 5- user;
				last = last - computer;
				System.out.printf("计算机取火柴数量：%d", computer);
				if(last == 0)
				{
					System.out.printf("\n计算机取了最后一个火柴，因此用户赢了！\n");
					break;
				}
			}
		}
	}
	/**
	 * Constructor
	 */
	public P10_16() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月13日上午10:21:57
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		System.out.printf("常胜将军问题求解！\n");
		System.out.printf("请先输入火柴的总量为：\n");
		Scanner sc =new Scanner(System.in);
		num = sc.nextInt();
		System.out.printf("火柴的总量为%d：", num);
		last = num;
		jiangjun();
	}

}
