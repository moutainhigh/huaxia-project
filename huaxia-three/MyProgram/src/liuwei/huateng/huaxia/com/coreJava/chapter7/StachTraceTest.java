/**
 * Title: StachTraceTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月31日下午4:03:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter7;

import java.util.Scanner;

/**
 * @class_name:StachTraceTest2019年12月31日
 * @comments:获取堆栈的的调用信息，就是几层的方法调用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日下午4:03:13
 */
public class StachTraceTest {

	/**
	 * 
	 */
	public StachTraceTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:03:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter n:");
		int n= in.nextInt();
		factorial(n);
	}
	/**
	 * @Title: factorial
	 *@Description: TODO
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:06:39
	 */
	public static int factorial(int n){
		System.out.println("factorial("+n+"):");
		Throwable t = new Throwable();
		StackTraceElement[] frames = t.getStackTrace();
		for(StackTraceElement f:frames)
			System.out.println(f);
			int r ;
		if(n<=1) r=1;
		else 
			r = n*factorial(n-1);
		System.out.println("return "+r);
		return r;
	}
}
