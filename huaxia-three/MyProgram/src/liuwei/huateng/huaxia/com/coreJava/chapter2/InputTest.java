/**
 * Title: InputTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月25日下午4:39:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Scanner;

/**
 * @class_name:InputTest2019年12月25日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月25日下午4:39:36
 */
public class InputTest {
	
	/**
	 * 
	 */
	public InputTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月25日下午4:39:36
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		//get first input
		System.out.print("What is your name?");
		String name = in.nextLine();
		
		//get second input
		System.out.print("How old are you ?");
		int age = in.nextInt();
		
		//display output on console
		System.out.println("Hello ,"+name+".Next year,you wil be "+(age+1));
	}

}
