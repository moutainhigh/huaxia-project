/**
 * Title: P10_13.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������5:43:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P10_132020��8��10��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������5:43:08
 */
public class P10_13 {

	/**
	 * Constructor
	 */
	public P10_13() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��10������5:43:08
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		int yufu;
		System.out.println("������㷨���!");
		System.out.print("��������������");
		Scanner input = new Scanner(System.in);
		yufu = input.nextInt();
		num = fish(yufu);
		System.out.println("num"+num);
	}
	/**
	 * @Title: fish
	 *@Description: TODO
	 *@param yufu ������㷨
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��10������5:45:14
	 */
	public static int fish(int yufu){
		int init;
		int n;
		int s;
		
		init = yufu+1;
		n = yufu-1;
		s = init;
		while(n!=0){
			s= 5*s+1;
			n--;
		}
		return s;
	}
}
