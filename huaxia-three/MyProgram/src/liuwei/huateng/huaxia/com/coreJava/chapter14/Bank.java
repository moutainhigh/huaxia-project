/**
 * Title: Bank.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月17日下午3:51:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter14;

import java.util.Arrays;

/**
 * @class_name:Bank2020年1月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月17日下午3:51:36
 */
public class Bank {
	private  double[] accounts;
	/**
	 * 
	 */
	public Bank(int n,double initialBalance) {
		// TODO Auto-generated constructor stub
		accounts = new double[n];
		Arrays.fill(accounts, initialBalance);
	}
	public void transfer(int from,int to,double amount){
		if(accounts[from] < amount)
			return;
		System.out.println(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d",amount,from,to);
		accounts[to] += amount;
		System.out.printf("Total Balance :%10.2f%n",getTotalBalance());
	}
	/**
	 * @Title: getTotalBalance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午3:57:26
	 */
	public double getTotalBalance()
	{
		double sum = -1;
		for(double a :accounts)
			sum+=a;
		return sum;
	}
	/**
	 * @Title: size
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午3:58:16
	 */
	public int size()
	{
		return accounts.length;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月17日下午3:51:36
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
