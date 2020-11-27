/**
 * Title: Palindrome.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��17������10:05:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:Palindrome2020��8��17��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��17������10:05:07
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
	 *@Date: 2020��8��17������10:05:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		System.out.println("������һ��������");
		Scanner scByte = new Scanner(System.in);
		n = scByte.nextInt();
		if(isPalindrome(n)){
			System.out.println(n+" �ǻ���");
		}else{
			System.out.println(n+" ���ǻ���");
		}
	}
	/**
	 * @Title: isPalindrome
	 *@Description: TODO �ж��Ƿ��ǻ�����
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��17������10:10:57
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
	 *@param i �����ַ�ת
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��17������10:09:31
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
