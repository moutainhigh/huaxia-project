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
 * @comments: �ݹ����׳�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������4:18:12
 */
public class P6_19 {

	/**
	 * Constructor
	 */
	public P6_19() {
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
	 *@Description: TODO �ݹ����׳�
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��10������4:19:16
	 */
	public static long fact(int n)
	{
		if(n <= 1)
			return 1;
		else
			return n* fact(n-1);
	}
}
