/**
 * Title: Fib.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��7������10:27:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:Fib2020��8��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��7������10:27:33
 */
public class Fib {
	private static int NUM = 13;
	/**
	 * Constructor
	 */
	public Fib() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��7������10:27:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		int fib[]=new int[NUM];
		fib[0] = 1;
		fib[1] = 1;
		for(i = 2;i<NUM;i++)
		{
			fib[i] = fib[i-1]+fib[i-2];
		}
		for(i =0 ;i< NUM;i++){
			System.out.println(i+"����������"+fib[i]);
		}
	}

}
