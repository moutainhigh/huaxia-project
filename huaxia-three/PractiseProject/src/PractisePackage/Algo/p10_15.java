/**
 * Title: p10_15.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��13������10:12:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:p10_152020��8��13��
 * @comments:�������⣬����ѧ����ֵ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��13������10:12:07
 */
public class p10_15 {
	/**
	 * @Title: Fibonacci
	 *@Description: TODO ����fibonacci����
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������10:14:18
	 */
	static int Fibonacci(int n){
		int t1,t2;
		if(n==1 || n ==2)
		{
			return 1;
		}
		else{
			t1 = Fibonacci(n-1);
			t2 = Fibonacci(n-2);
			return t1+t2;
		}
	}
	/**
	 * Constructor
	 */
	public p10_15() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��13������10:12:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n,num;
		System.out.println("���Ӳ����������!\n");
		System.out.println("����ʱ�䣺");
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		num = Fibonacci(n);
		System.out.println("����"+n+"���µ�ʱ�䣬���ܷ�ֳ��"+num+"�����ӣ�	");
	}
}
