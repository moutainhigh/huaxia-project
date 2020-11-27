/**
 * Title: P6_20.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������4:30:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P6_202020��8��10��
 * @comments:��Բ������pi
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������4:30:50
 */
public class P6_20 {

	/**
	 * Constructor
	 */
	public P6_20() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��10������4:30:50
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		System.out.println("�������и������");
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		cyclotomic(n);
	}
	/**
	 * @Title: cyclotomic
	 *@Description: TODO ��Բ������pi
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020��8��10������4:31:28
	 */
	public static void cyclotomic(int n)
	{
		int i,s;
		double k,len;
		i =0;
		k=3.0;
		len = 1.0;
		s = 6;
		while(i<= n)
		{
			System.out.printf("��%2d���иΪ��%5d���Σ�PI=%.24f\n",i,s,k*Math.sqrt(len));
			s*=2;
			len = 2-Math.sqrt(4-len);
			i++;
			k*=2.0;
		}
	}
}
