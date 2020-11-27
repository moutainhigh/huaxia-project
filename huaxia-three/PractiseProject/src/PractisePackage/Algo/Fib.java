/**
 * Title: Fib.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月7日上午10:27:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:Fib2020年8月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月7日上午10:27:33
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
	 *@Date: 2020年8月7日上午10:27:33
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
			System.out.println(i+"月兔子总数"+fib[i]);
		}
	}

}
