/**
 * Title: P6_18.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������4:18:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P6_182020��8��10��
 * @comments: ѭ������׳�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������4:18:12
 */
public class P6_18 {

	/**
	 * Constructor
	 */
	public P6_18() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��10������4:18:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i ;
		System.out.print("������Ҫ��׳˵�һ��������");
		Scanner input = new Scanner(System.in);
		i = input.nextInt();
		System.out.println(i+"�Ľ׳˽��Ϊ��"+fact(i));
	}
	/**
	 * @Title: fact
	 *@Description: TODO ѭ������׳�
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��10������4:19:16
	 */
	public static long fact(int n)
	{
		int i;
		long result = 1;
		for(i =1;i<=n;i++)
		{
			result *= i;
		}
		return result;
	}
}
