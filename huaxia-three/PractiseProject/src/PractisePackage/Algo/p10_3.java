/**
 * Title: p10_3.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��17������11:12:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:p10_32020��8��17��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��17������11:12:32
 */
public class p10_3 {

	/**
	 * Constructor
	 */
	public p10_3() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��17������11:12:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ji = {0},tu ={0};
		int head,foot;
		System.out.printf("����ͬ��������⣺\n");
		System.out.printf("����ͷ��:");
		Scanner input = new Scanner(System.in);
		head =input.nextInt();
		System.out.printf("�������:");
		foot = input.nextInt();
		 JTTL( head, foot,tu, ji);
		 System.out.printf("����:%dֻ�������У�%dֻ��\n",ji[0],tu[0]);
	}
	/**
	 * @Title: JTTL
	 *@Description: TODO
	 *@param head
	 *@param foot
	 *@param tu
	 *@param ji
	 *@author: LiuWei
	 *@Date: 2020��8��17������11:13:48
	 */
	static void JTTL(int head,int foot,int[] tu,int[] ji)
	{
		tu[0] = (foot -2*head)/2;
		ji[0] = head-tu[0];
	}
}
