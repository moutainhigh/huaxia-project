/**
 * Title: Exchange.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月7日下午1:11:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:Exchange2020年8月7日
 * @comments:贪婪算法进行零钱的算法 
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月7日下午1:11:12
 */
public class Exchange2 {
	private static int MAXN = 9;
	private static double parvalue[] = {100,50,20,10,5,2,1,0.5,0.2,0.1};
	static int num[] = new int[MAXN];
	/**
	 * Constructor
	 */
	public Exchange2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午1:11:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		float m;
		System.out.println("请输入找零的金额：");
		Scanner sc = new Scanner(System.in);
		 m = sc.nextFloat();
		 exchange(m);
		 System.out.printf("\n%.2f元零钱的组成：\n",m);
		 for(i=0;i<MAXN;i++){
			 if(num[i]>0){
				 System.out.printf("%6.2f:%d张\n",(float)parvalue[i],num[i]);
			 }
		 }
	}
	/**
	 * @Title: exchange
	 *@Description: TODO 交换函数
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午1:45:59
	 */
	public static int exchange(double n){
		int i,j;
		for(i=0;i<MAXN;i++)
			if(n>parvalue[i] )break;
		while(n >0 && i<MAXN){
			if(n >= parvalue[i]){
				n-=parvalue[i];
				num[i]++;
			}else i++;
		}
		return 0;
	}
}
