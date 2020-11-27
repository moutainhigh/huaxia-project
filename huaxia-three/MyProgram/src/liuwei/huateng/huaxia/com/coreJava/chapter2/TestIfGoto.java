/**
 * Title: TestIfGoto.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月26日上午10:25:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Scanner;

/**
 * @class_name:TestIfGoto2019年12月26日
 * @comments: break label; 跳出带标签的语句块的末尾
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月26日上午10:25:24
 */
public class TestIfGoto {

	/**
	 * 
	 */
	public TestIfGoto() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月26日上午10:25:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		label:
		{
			System.out.println("进入了label语句块");
			Scanner sc = new Scanner(System.in);
			int condition = sc.nextInt();
			if(condition < 0) break label;
			System.out.println("label语句将要结束");
		}
		System.out.println("跳出了label语句");
	}

}
