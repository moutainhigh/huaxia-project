/**
 * Title: Retirement2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月25日下午5:10:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Scanner;

/**
 * @class_name:Retirement22019年12月25日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月25日下午5:10:08
 */
public class Retirement2 {

	/**
	 * 
	 */
	public Retirement2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月25日下午5:10:08
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("How much money will you contribute every year?");
		double payment = in.nextDouble();
		
		System.out.print("Interest rate in %:");
		double interestRate = in.nextDouble();
		
		double balance = 0;
		int years = 0;
		String input = null;
		//update account balance while user isnot ready to retire
		do{
			//add this year's payment add interest
			balance += payment;
			double interest = balance * interestRate/100;
			balance += interest;
			years++;
			//print curent balance 
			System.out.printf("After year %d,your balance is %,.2f%n",years,balance);
			//ask if ready to retire and get input
			System.out.println("Ready to retire?(Y/N)");
			input = in.next();
		}while(input.equals("N"));
	}

}
