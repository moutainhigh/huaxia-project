/**
 * Title: P5_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日下午12:27:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P5_12020年8月10日
 * @comments: 顺序查找法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日下午12:27:14
 */
public class P5_1 {
	static final int N = 15;
	/**
	 * Constructor
	 */
	public P5_1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午12:27:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x,n,i;
		int[] shuzu= new int[N];
		for(i =0;i<N;i++){
			shuzu[i] = (int)(100+Math.random()*(100+1));
		}
		System.out.print("顺序查找算法演示！	\n");
		System.out.print("数据序列：\n");
		for(i =0;i<N;i++){
			System.out.print(" "+shuzu[i]);
		}
		System.out.print("\n\n");
		System.out.print("输入要查找的数");
		Scanner input = new Scanner(System.in);
		x = input.nextInt();
		n = searchFun(shuzu,N,x);
		if(n<0)
		{
			System.out.println("没找到数据："+x);
		}else
		{
			System.out.println("数据"+x+"位于数组的第"+(n+1)+"个元素处。")	;
		}
	}
	/**
	 * @Title: searchFun
	 *@Description: TODO 顺序查找法
	 *@param a
	 *@param n
	 *@param x
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月10日下午1:51:14
	 */
	public static int searchFun(int a[],int n,int x){
		int i,f = -1;
		for(i =0;i<n;i++)
		{
			if(x == a[i])
			{
				 f =i; 
					break;
			}
		}
		return f;
	}
}
