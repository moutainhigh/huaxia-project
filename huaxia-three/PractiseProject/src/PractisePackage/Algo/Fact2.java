/**
 * Title: Fact2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��7������12:38:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:Fact22020��8��7��
 * @comments: 
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��7������12:38:47
 */
public class Fact2 {

	/**
	 * Constructor
	 */
	public Fact2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��7������12:38:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		System.out.println("������Ҫ��׳˵�һ��������");
		Scanner sc = new Scanner(System.in);
		i = sc.nextInt();
		System.out.println(i+"�Ľ׳˽��Ϊ"+fact(i));
	}
	/**
	 * @Title: fact
	 *@Description: TODO
	 *@param n �׳˵ݹ麯��
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��7������12:39:45
	 */
	public static int fact(int n){
		if(n<1)
			return 1;
		else
			return n*fact(n-1);
	}
}
