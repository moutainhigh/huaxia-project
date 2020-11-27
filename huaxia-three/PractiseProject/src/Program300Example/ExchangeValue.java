/**
 * Title: ExchangeValue.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日下午5:24:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:ExchangeValue2020年9月21日
 * @comments:通过异或运算实现变量的互换
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日下午5:24:21
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
	 *@Date: 2020年9月21日下午5:24:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入变量A的值");
		long A = scan.nextLong();
		System.out.println("请输入变量B的值");
		long B = scan.nextLong();
		System.out.println("A="+A+"\tB="+B);
		System.out.println("\t执行变量的互换。。。\t");
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
