/**
 * Title: Test5.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月26日上午10:06:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Scanner;

/**
 * @class_name:Test52019年12月26日
 * @comments:break read_data; 相当于goto语句，
 * 结束后跳到带标签的语句块的末尾
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月26日上午10:06:18
 */
public class Test5 {

	/**
	 * 
	 */
	public Test5() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月26日上午10:06:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = 0;
		read_data:
		{
			System.out.println("进入了while循环");
			while(n>-10){
				System.out.println("进入了while循环");
				for(int i=0;i<3;i++){
					System.out.println("for循环中i的值"+i);
					System.out.print("Enter a number >= 0:");
					n = in.nextInt();
					if(n<0)
						break read_data;
				}
			}
			System.out.println("语句块结束");
		}
		System.out.println("while 循环结束！");
		if(n<0){
			System.out.println("n小于0");
		}
		else
		{
			System.out.println("n大于0");
		}
	}

}
