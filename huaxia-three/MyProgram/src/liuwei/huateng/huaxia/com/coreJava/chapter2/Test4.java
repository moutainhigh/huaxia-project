/**
 * Title: Test4.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月26日上午9:53:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Scanner;

/**
 * @class_name:Test42019年12月26日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月26日上午9:53:48
 */
public class Test4 {

	/**
	 * 
	 */
	public Test4() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月26日上午9:53:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while(true){
			System.out.println("Select an option (1,2,3,4） ");
			int choice = in.nextInt();
			switch(choice){
			case 1:
				System.out.println("choose "+choice);
				break;
			case 2:
				System.out.println("choose "+choice);
				break;
			case 3:
				System.out.println("choose "+choice);
				break;
			case 4:
				System.out.println("choose "+choice);
				break;
			default:
				System.out.println("choose "+choice);
				break;
			}
		}
	}
}
