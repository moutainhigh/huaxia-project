/**
 * Title: P3_2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������10:48:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P3_22020��8��10��
 * @comments:�����㷨������ӵĹ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������10:48:07
 */
public class P3_2 {

	/**
	 * Constructor
	 */
	public P3_2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��10������10:48:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("�����㷨������Ӳ������⣡");
		System.out.println("������ʱ�䣺");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int num = fibonacci(n);
		System.out.println("����"+n+"���µ�ʱ�䣬���ܷ�ֳ��"+num+"�����ӣ�");
	}
	/**
	 * @Title: fibonacci
	 *@Description: TODO ����fibnoacci����
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��10������10:50:47
	 */
	public static int fibonacci(int n)
	{
		int t1,t2;
		if(n == 1 || n==2)
			return 1;
		else
		{
			t1 = fibonacci(n-1);
			t2 = fibonacci(n-2);
			return t1+t2;
		}
	}
}
