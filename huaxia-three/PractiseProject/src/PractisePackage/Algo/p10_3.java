/**
 * Title: p10_3.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日上午11:12:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:p10_32020年8月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日上午11:12:32
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
	 *@Date: 2020年8月17日上午11:12:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ji = {0},tu ={0};
		int head,foot;
		System.out.printf("鸡兔同笼问题求解：\n");
		System.out.printf("输入头数:");
		Scanner input = new Scanner(System.in);
		head =input.nextInt();
		System.out.printf("输入脚数:");
		foot = input.nextInt();
		 JTTL( head, foot,tu, ji);
		 System.out.printf("鸡有:%d只，兔子有：%d只。\n",ji[0],tu[0]);
	}
	/**
	 * @Title: JTTL
	 *@Description: TODO
	 *@param head
	 *@param foot
	 *@param tu
	 *@param ji
	 *@author: LiuWei
	 *@Date: 2020年8月17日上午11:13:48
	 */
	static void JTTL(int head,int foot,int[] tu,int[] ji)
	{
		tu[0] = (foot -2*head)/2;
		ji[0] = head-tu[0];
	}
}
