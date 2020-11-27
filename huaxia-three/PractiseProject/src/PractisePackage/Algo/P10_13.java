/**
 * Title: P10_13.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午5:43:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P10_132020年8月10日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午5:43:08
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
	 *@Date: 2020年8月10日下午5:43:08
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		int yufu;
		System.out.println("渔夫捕鱼算法求解!");
		System.out.print("请先输入渔夫个数");
		Scanner input = new Scanner(System.in);
		yufu = input.nextInt();
		num = fish(yufu);
		System.out.println("num"+num);
	}
	/**
	 * @Title: fish
	 *@Description: TODO
	 *@param yufu 渔夫捕鱼算法
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午5:45:14
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
