/**
 * Title: ExchangeValue.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��21������5:24:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:ExchangeValue2020��9��21��
 * @comments:ͨ���������ʵ�ֱ����Ļ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��21������5:24:21
 */
public class ExchangeValue {

	/**
	 * Constructor
	 */
	public ExchangeValue() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��21������5:24:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("���������A��ֵ");
		long A = scan.nextLong();
		System.out.println("���������B��ֵ");
		long B = scan.nextLong();
		System.out.println("A="+A+"\tB="+B);
		System.out.println("\tִ�б����Ļ���������\t");
	/*	A = A^B;
		B = B^A;
		A = A^B;*/
		/*A = A^B;
		B = A^B;
		A = A^B;*/
		B = A^B;
		A = A^B;
		B = A^B;
		System.out.println(" A="+A+"\tB="+B);
	}

}
