/**
 * Title: Guess.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月7日上午10:14:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:Guess2020年8月7日
 * @comments:猜数字的游戏
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月7日上午10:14:20
 */
public class Guess {

	/**
	 * Constructor
	 */
	public Guess() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月7日上午10:14:20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int oldprice,price =0,i=0;
		System.out.println("请首先设置商品的真实价格：");
		Scanner sc = new Scanner(System.in);
		oldprice = sc.nextInt();
		System.out.println("请输入试猜的价格：");
		while(oldprice != price)
		{
			i++;
			System.out.println("参与者：");
			price = sc.nextInt();
			System.out.println("主持人：");
			if(price > oldprice)
			{
				System.out.println("高了");
			}
			else if(price < oldprice)
			{
				System.out.println("低了");
			}else{
				System.out.println("恭喜你，答对了，该商品属于你了！\n\n你一共试猜了"+i+"次");
			}
		}
	}

}
