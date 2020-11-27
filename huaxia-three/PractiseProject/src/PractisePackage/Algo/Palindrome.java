/**
 * Title: Palindrome.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日上午10:05:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:Palindrome2020年8月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日上午10:05:07
 */
public class Palindrome {

	/**
	 * Constructor
	 */
	public Palindrome() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月17日上午10:05:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		System.out.println("请输入一个整数：");
		Scanner scByte = new Scanner(System.in);
		n = scByte.nextInt();
		if(isPalindrome(n)){
			System.out.println(n+" 是回文");
		}else{
			System.out.println(n+" 不是回文");
		}
	}
	/**
	 * @Title: isPalindrome
	 *@Description: TODO 判断是否是回文数
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月17日上午10:10:57
	 */
	public static boolean isPalindrome(int n)
	{
		int m = reverse(n);
		if(m == n)
			return true;
		else 
			return false;
	}
	/**
	 * @Title: reverse
	 *@Description: TODO
	 *@param i 把数字反转
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月17日上午10:09:31
	 */ 
	public static int reverse(int i)
	{
		int s,j = 0;
		s = i;
		while(s != 0){
			j= j*10+s%10;
			s= s/10;
		}
		return j;
	}
}
