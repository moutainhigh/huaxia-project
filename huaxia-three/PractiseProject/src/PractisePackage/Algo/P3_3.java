/**
 * Title: P3_3.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������11:02:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P3_32020��8��10��
 * @comments: �ݹ���׳�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������11:02:53
 */
public class P3_3 {

	/**
	 * Constructor
	 */
	public P3_3() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��10������11:02:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		System.out.println("������Ҫ��׳˵�һ��������");
		Scanner sc = new Scanner(System.in);
		i = sc.nextInt();
		System.out.println(i+"�Ľ׳˽��Ϊ��"+fact(i));
	}
	/**
	 * @Title: fact
	 *@Description: TODO �׳˺���
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��10������11:03:57
	 */
	private static  long fact(int n){
		if(n <= 1)
			return  1;
		else 
			return n* fact(n-1);
	}
}
