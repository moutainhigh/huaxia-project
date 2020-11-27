/**
 * Title: Play.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月7日下午3:48:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Random;
import java.util.Scanner;

/**
 * @class_name:Play2020年8月7日
 * @comments:模拟算法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月7日下午3:48:48
 */
public class Play {
	private static Random random = new Random();
	/**
	 * Constructor
	 */
	public Play() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午3:48:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int c;//参赛人数
		int n;
		int i,m;
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("设置骰子数量（输入0退出）");
			n = sc.nextInt();
			if(n ==0) break;
			System.out.println("\n输入本轮参赛人数（ 输入0退出）");
			c = sc.nextInt();
			if(c ==0 ) break;
			for( i=0;i<c;i++){
				System.out.printf("\n第%d选手的骰子数量为:\n",i+i);
				play(n);
			}
		}while(true);
	}
	/**
	 * @Title: play
	 *@Description: TODO n个骰子的点数
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午3:53:14
	 */
	public static void play(int n){
		int i,m=0,t=0;
		for(i =0;i<n;i++){
			t = random.nextInt(6)+1;
			m+=t;
			System.out.printf("\t第%d粒:%d;\n",i+1,t);
		}
		System.out.printf("\t总点数为:%d\n",m);
	}
}
