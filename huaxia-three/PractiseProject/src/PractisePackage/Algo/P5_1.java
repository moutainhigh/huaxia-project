/**
 * Title: P5_1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������12:27:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P5_12020��8��10��
 * @comments: ˳����ҷ�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������12:27:14
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
	 *@Date: 2020��8��10������12:27:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x,n,i;
		int[] shuzu= new int[N];
		for(i =0;i<N;i++){
			shuzu[i] = (int)(100+Math.random()*(100+1));
		}
		System.out.print("˳������㷨��ʾ��	\n");
		System.out.print("�������У�\n");
		for(i =0;i<N;i++){
			System.out.print(" "+shuzu[i]);
		}
		System.out.print("\n\n");
		System.out.print("����Ҫ���ҵ���");
		Scanner input = new Scanner(System.in);
		x = input.nextInt();
		n = searchFun(shuzu,N,x);
		if(n<0)
		{
			System.out.println("û�ҵ����ݣ�"+x);
		}else
		{
			System.out.println("����"+x+"λ������ĵ�"+(n+1)+"��Ԫ�ش���")	;
		}
	}
	/**
	 * @Title: searchFun
	 *@Description: TODO ˳����ҷ�
	 *@param a
	 *@param n
	 *@param x
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��10������1:51:14
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
