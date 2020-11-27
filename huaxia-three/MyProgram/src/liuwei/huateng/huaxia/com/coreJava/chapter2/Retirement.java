/**
 * Title: Retirement.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月25日下午4:57:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Scanner;

/**
 * @class_name:Retirement2019年12月25日
 * @comments:计算退休金
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月25日下午4:57:00
 */
public class Retirement {

	/**
	 * 
	 */
	public Retirement() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月25日下午4:57:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("How much money do you need to retire?");
		double goal = in.nextDouble();
		
		System.out.print("How much money will you contribute every year?");
		double payment = in.nextDouble();
		
		System.out.print("Interest rate in %:");
		double interestRate = in.nextDouble();
		
		double balance = 0;
		int years = 0;
		
		// update account balance while goal isnot reached
		while(balance < goal)
		{
			balance += payment;
			double interest = balance * interestRate/100;
			balance += interest;
			years++;
		}
		System.out.println("You can retire in "+years +" years. ");
	}

}
